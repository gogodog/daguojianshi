<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="/admin/css/xcConfirm.css?v=${staticVersion}"/>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
	    <div class="public-nav">您当前的位置：<a href="">公告管理</a>><a href="">公告内容管理</a>><a href="javascript:void(0)">公告列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
		    	<h3 style="display: inline-block;">公告列表</h3>
		    	<div class="public-content-right fr">
				  <a href="javascript:void(0)" id="saveAnnounce"
				     style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加公告</a>
			    </div>
			</div>
			<div class="public-content-cont">
			   <form id="selectForm" action="${contextPath}/admin/announce/list" method="post">
		        <input type="hidden" name="currentPage">
		             <p style="margin-bottom:10px">
		               <label>状态:</label><select name="status"><option value="">全部</option><#list upDownStatus as status><option value="${status}" <#if condition.status.key=="${status.key}">selected</#if>>${status.value}</option></#list></select>&nbsp;&nbsp;
		               <label>展示时间:</label><input type="text" name="time_From" value="<#if condition.timeFrom??>${condition.timeFrom?date}</#if>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px"/> 
				        - <input type="text" value="<#if condition.timeTo??>${condition.timeTo?date}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px" name="time_To"/>&nbsp;&nbsp;
		               <input type="button" value="查询" name="conditionButton" class="select-btn"/>
		             </p>
		        </form>
			    <table class="public-cont-table">
					<tr>
					    <th style="width:11%">创建时间</th>
					    <th style="width:11%">展示时间</th>
						<th style="width:10%">状态</th>
						<th style="width:10%">类型</th>
						<th style="width:45%">消息</th>
						<th style="width:13%">操作</th>
					</tr>
					<#list pageInfo.objects as result>
					  <tr>
					     <td>${result.create_time?datetime}</td>		
					     <td><#if result.show_time??>${result.show_time?datetime}</#if></td>	
					     <td>${result.status.value}</td>
					     <td>${result.type.value}</td>	
					     <td>${result.message}</td>	
					     <td>
					       <div class="table-fun-1">
					         <#if result.status=='UP'>
					            <a href="javascript:void(0)" onclick="changeStatus('${result.id}','${result.status}');">下架</a>
					         <#elseif result.status=='DOWN'>
					            <a href="javascript:void(0)" onclick="changeStatus('${result.id}','${result.status}');">上架</a>
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
<script src="/admin/js/jquery-1.11.1.min.js?v=${staticVersion}"></script>
<script src="/admin/js/page.js?v=${staticVersion}" charset="utf-8"></script>
<script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js?v=${staticVersion}"></script>
<script src="/admin/js/confirm/xcConfirm.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="/cps/css/alertify.css?v=${staticVersion}">
<script src="/cps/js/alertify.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
<script>
var contextPath="${contextPath}";
function changeStatus(id,status){
	var txt = "";
	var operate_status = "";
	if(status == 'UP'){
		txt = '您确定要下架';
		operate_status = 'DOWN';
	}else if(status == 'DOWN'){
		txt = '您确定要上架';
		operate_status = 'UP';
	}
	if(txt!=''){
		debugger;
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
			updateStatus(id,operate_status);
		}})
	}
}

function updateStatus(id,status){
	$.ajax({
		async:false,
		data:{id:id,status:status},
		dataType: "json",
		url:contextPath+"/admin/announce/updateStatus",
		type:"POST",
		success:function(data) {
            if(data.error){
            	if(data.errorCode!='SUCCESS'){
            		alert(data.errorMessage);
            	}
            }else{
            	window.location.href=contextPath+"/admin/announce/list";
            }
        }, 
        error:function(){
            console.log("服务器繁忙...");
        }
	});
}

$("#saveAnnounce").click(function(){
	   var txt='类型&nbsp;:&nbsp;<select id="type"><option value="formal">正式</option><option value="informal">非正式</option></select><br><br>';
	   txt+='公告&nbsp;:&nbsp;<textarea id="message" style="width:280px;height:150px"></textarea>';
	   alertify.confirm(txt, function () {
		   var message = $("#message").val();
		   var type = $("#type").val();
		   $.ajax({
	 		   async:false,
	 		   data:{type:type,message:message},
	 		   dataType: "json",
	 		   url:contextPath+"/admin/announce/save",
	 		   type:"POST",
	 		   success:function(data) {
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
	   });
})
</script>
</body>
</html>