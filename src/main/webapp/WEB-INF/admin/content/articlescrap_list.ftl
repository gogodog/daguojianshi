<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="/admin/css/xcConfirm.css?v=${staticVersion}"/>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">文章管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">文章列表</h3>
				<div class="public-content-right fr">
				  <a href="/admin/atcp/articlescrap" 
				     style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加文章</a>
			    </div>
			</div>
			<div class="public-content-cont">
			  <form id="selectForm" action="${contextPath}/admin/atcp/articlescrapList" method="post">
			    <input type="hidden" name="currentPage">
			    <p style="margin-bottom:10px">
			        <label>文章标题:</label><input type="text" name="title" value="${(condition.title)!''}"/>&nbsp;&nbsp;
			        <label>文章展示时间:</label><input type="text" name="showTimeFrom" value="<#if condition.showTimeFrom??>${condition.showTimeFrom?date}</#if>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px"/> 
			        - <input type="text" value="<#if condition.showTimeTo??>${condition.showTimeTo?date}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px" name="showTimeTo"/>&nbsp;&nbsp;
			        <label>作者:</label><input type="text" name="author" value="${(condition.author)!''}"/>&nbsp;&nbsp;
			        <label>分类:</label><select name="type"><option value="">全部</option><#list articlescrapTypes as type><option value="${type}" <#if condition.type.key=="${type.key}">selected</#if>>${type.value}</option></#list></select>&nbsp;&nbsp;
			        <label>状态:</label><select name="status"><option value="">全部</option><#list upDownStatus as status><option value="${status}" <#if condition.status.key=="${status.key}">selected</#if>>${status.value}</option></#list></select>&nbsp;&nbsp;
			        <input type="button" value="查询" name="conditionButton" class="select-btn"/>
			    </p>
			  </form>
			    <table class="public-cont-table">
					<tr>
					    <th style="width:5%">文章id</th>
						<th style="width:12%">文章标题</th>
						<th style="width:20%">文章内容</th>
						<th style="width:10%">文章展示时间</th>
						<th style="width:8%">分类</th>
						<th style="width:5%">状态</th>
						<th style="width:7%">作者</th>
						<th style="width:8%">创建时间</th>
						<th style="width:8%">修改时间</th>
						<th style="width:18%">操作</th>
					</tr>
					<#list pageInfo.objects as articlescrap>
					  <tr>
					     <td>${articlescrap.id}</td>			
					     <td>${articlescrap.title}</td>						
					     <td>${articlescrap.sub_content}</td>
					     <td>${articlescrap.show_time?datetime}</td>
					     <td>${articlescrap.type.value}</td>	
					     <td>${articlescrap.status.value}</td>
					     <td>${articlescrap.author}</td>
					     <td>${articlescrap.create_time?datetime}</td>
					     <td>${articlescrap.update_time?datetime}</td>
					     <td>
					     	<div class="table-fun-1">
					     	<!--	<a href="/admin/atcp/articlescrap?articlescrapId=${articlescrap.id}">修改</a> -->
					       	    <a href="/admin/atcp/info?articlescrapId=${articlescrap.id}">修改信息</a>
					       	    <a href="/admin/atcp/content?articlescrapId=${articlescrap.id}">修改内容</a>
					       	    <a href="/admin/atcp/previewArticlescrap?articlescrapId=${articlescrap.id}">预览</a>
					     		<#if articlescrap.status == 'INIT' || articlescrap.status == 'DOWN'>
					     		   <a href="javascript:void(0)" onclick="updateStatus('${articlescrap.id}','UP');">上架</a>
					     		<#elseif articlescrap.status == 'UP'>   
					     		   <a href="javascript:void(0)" onclick="updateStatus('${articlescrap.id}','DOWN');">下架</a>
					     		</#if>
					     		<a href="javascript:void(0)" onclick="deleteArticlescrap('${articlescrap.id}');">删除</a>
					     		<a href="/admin/cmt/comments?articlescrapId=${articlescrap.id}">评论</a>
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
<script src="/admin/js/confirm/xcConfirm.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
<script src="/admin/js/page.js?v=${staticVersion}" charset="utf-8"></script>
<script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js?v=${staticVersion}"></script>
<script>
var contextPath="${contextPath}";
function deleteArticlescrap(articlescrapId){
	var txt = "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		window.location.href=contextPath+"/admin/atcp/deleteArticlescrap?articlescrapId="+articlescrapId;
	}})
}

function updateStatus(articlescrapId,status){
	var txt = "";
	if(status == 'UP'){
		txt = "您确定要上架吗？";
	}else if(status == 'DOWN'){
		txt = "您确定要下架吗？";
	}
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		changeStatus(articlescrapId,status);
	}})
}

function changeStatus(articlescrapId,status){
	  $.ajax({
		   data:{articlescrapId:articlescrapId,status:status},
		   dataType: "json",
		   url:contextPath+"/admin/atcp/updateStatus",
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
}
</script>
</body>
</html>