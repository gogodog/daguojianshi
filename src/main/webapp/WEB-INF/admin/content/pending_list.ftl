<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">文章管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">文章列表</h3>
			</div>
			<div class="public-content-cont">
			  <form id="selectForm" action="${contextPath}/admin/pding/docms" method="post">
			    <input type="hidden" name="currentPage">
			    <p style="margin-bottom:10px">
			    <label>分类:</label><select name="type"><option value="">全部</option><#list typeList as type><option value="${type}" <#if condition.type.key=="${type.key}">selected</#if>>${type.value}</option></#list></select>&nbsp;&nbsp;
		           <label>状态:</label><select name="status"><option value="">全部</option><#list statusList as status><option value="${status}" <#if condition.status.key=="${status.key}">selected</#if>>${status.value}</option></#list></select>&nbsp;&nbsp;
                <label>标题:<input type="text" name="title" value="${condition.title}"></label>
                <label><input type="button" value="查询" name="conditionButton"/></label>
			    </p>
			  </form>
			    <table class="public-cont-table">
					<tr>
					    <th style="width:20%">标题</th>
						<th style="width:8%">状态</th>
						<th style="width:7%">类型</th>
						<th style="width:7%">作者</th>
						<th style="width:25%">时间</th>
						<th style="width:25%">操作</th>
					</tr>
					<#list pageInfo.objects as object>
					  <tr>
					     <td>${object.title}</td>
                         <td>${object.status.value}</td>
                         <td>${object.type.value}</td>
                         <td>${object.author}</td>
                         <td>
                            提审时间：${object.create_time?string("yyyy-MM-dd HH:mm:ss")}<br>
                            <#if object.audit_time??>审核时间：${object.audit_time?string("yyyy-MM-dd HH:mm:ss")}<br></#if>
                            <#if object.publish_time??>发布时间：${object.publish_time?string("yyyy-MM-dd HH:mm:ss")}<br></#if>
                         </td>
                         <td>
                           <div class="table-fun-1">
                            <a href="${contextPath}/admin/pding/previewPending?aid=${object.id}">查看</a>     
                            <#if object.status == 'AUDIT_PENDING'>
                              <a href="javascript:void(0)" onclick="showAudit('${object.id}');">审核</a>  
                            </#if>
                            <#if object.status == 'PUBLISH_PENDING'>
                              <a href="javascript:void(0)" onclick="showPublish('${object.id}');">发布</a>  
                            </#if>
                            <#if object.status == 'Audit_FAIL'>
                              <a href="javascript:void(0)" onclick="showAuditFailDesc('${object.audit_desc}');">拒绝原因</a>  
                            </#if>
                           </div>
                         </td>
				     </tr>
				   </#list>
				</table>
				<#include "/admin/common/page.ftl">
			</div>
		</div>
	</div>
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script src="${contextPath}/admin/js/page.js" charset="utf-8"></script>
<script language="javascript" src="${contextPath}/admin/js/My97DatePicker/wdatePicker.js"></script>
<script src="${contextPath}/admin/js/page.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/admin/css/alertify.css">
<script src="${contextPath}/admin/js/alertify.js" type="text/javascript" charset="utf-8"></script>
<script>
var contextPath="${contextPath}";
function showAuditFailDesc(audit_desc){
	var message = "<font size='4'>拒绝原因</font><p><strong>"+audit_desc+"</strong></p><br/>";  
    alertify.alert(message);  
}

function showAudit(aid){
	var message = "<font size='3'>审核不通过需要给出不通过的原因</font><p style='float:left'>审核&nbsp;:&nbsp<select id='auditStatus' onchange='changeDescShow(this)'><option value='PUBLISH_PENDING'>通过</option><option value='Audit_FAIL'>不通过</option></select></p><br/><br/>";  
    alertify.prompt(message, function (val, e) {  
        if(e) {  
     	   var status = $("#auditStatus").val();
 		   if(status == 'Audit_FAIL'){
 			    if(val==null||val.length<2){
 			    	alertify.error("审核不通过需要给出不通过的原因");  
     				return;
 			    }
 		   }
 		   $.ajax({
 	 		   async:false,
 	 		   data:{aid:aid,status:status,audit_desc:val},
 	 		   dataType: "json",
 	 		   url:contextPath+"/admin/pding/audit",
 	 		   type:"POST",
 	 		   success:function(data) {
 	                if(data.error){
 	                	alertify.error(data.errorMessage);
 	                }else{
 	                	alert("操作成功");      
 	                	location.reload();
 	                }
 	            }, 
 	            error:function(){
 	                alertify.error("服务器繁忙...");  
 	            }
 	        })
        } else {  
            alertify.error("审核不通过需要给出不通过的原因");  
        }  
    }, "Enter a value")
   }; 
    
    function showPublish(aid){
       var txt='<span>是否立刻展示&nbsp;:&nbsp;<input type="checkbox" id="showNow" onclick="showtime(this);"/></span>';
 	   txt+='<span id="showTimeSpan">展示时间&nbsp;:&nbsp;<input type="text" name="show_time" onClick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'});" class="Wdate" style="width:200px"><br></span>';
 	   txt+='访问量基数&nbsp;:&nbsp;<input type="text" name="visits" style="width:200px;height:20px"><br>';
 	  
 	   alertify.confirm(txt, function () {
 		   var showNow = 0;
 		   var show_time = null;
 		   if($("#showNow").is(':checked')){
 			  showNow = 1;
 		   }else{
 			  show_time = $("input[name='show_time']").val();
 		   }
 		   var visits = $("input[name='visits']").val();
 		   $.ajax({
 	 		   async:false,
 	 		   data:{aid:aid,show_time:show_time,visits:visits,showNow:showNow},
 	 		   dataType: "json",
 	 		   url:contextPath+"/admin/pding/publish",
 	 		   type:"POST",
 	 		   success:function(data) {
 	 			    debugger;
 	                if(data.error){
 	                	alertify.error(data.errorMessage);
 	                }else{
 	                	alert('操作成功');
 	                	location.reload();
 	                }
 	            }, 
 	            error:function(){
 	            	alertify.error("服务器繁忙...");
 	            }
 	        })
 	   }, function() {
// 		   alertify.error("cancel");
 	       // user clicked "cancel"
 	   });
    }
    
    function showtime(item){
    	if($(item).is(':checked')) {
			$("#showTimeSpan").hide();
		}else{
			$("#showTimeSpan").show();
		}
    }
    
</script>
</body>
</html>