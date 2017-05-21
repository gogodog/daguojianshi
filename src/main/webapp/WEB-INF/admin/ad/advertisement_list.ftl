<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="${contextPath}/admin/css/xcConfirm.css"/>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">广告管理</a><a href="">广告列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">广告列表</h3>
				<div class="public-content-right fr">
				  <a href="${contextPath}/admin/ad" 
				     style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加广告</a>
			    </div>
			</div>
			<div class="public-content-cont">
			  <form id="selectForm" action="${contextPath}/admin/adList" method="post">
			    <input type="hidden" name="currentPage">
			    <p style="margin-bottom:10px">
			        <label>广告描述:</label><input type="text" name="adDesc" value="${(condition.adDesc)!''}"/>&nbsp;&nbsp;
			        <label>广告位置:</label><select name="adPosition"><option value="">全部</option><#list adPositions as position><option value="${position}" <#if condition.adPosition.key=="${position.key}">selected</#if>>${position.value}</option></#list></select>&nbsp;&nbsp;
			        <label>状态:</label><select name="status"><option value="">全部</option><#list upDownStatus as status><option value="${status}" <#if condition.status.key=="${status.key}">selected</#if>>${status.value}</option></#list></select>&nbsp;&nbsp;
			        <input type="button" value="查询" name="conditionButton" class="select-btn"/>
			    </p>
			  </form>
			    <table class="public-cont-table">
					<tr>
					    <th style="width:5%">广告id</th>
						<th style="width:10%">广告描述</th>
						<th style="width:10%">广告图片</th>
						<th style="width:10%">点击广告链接</th>
						<th style="width:10%">广告位置</th>
						<th style="width:10%">开始展示时间</th>
						<th style="width:10%">结束展示时间</th>
						<th style="width:5%">状态</th>
						<th style="width:8%">创建时间</th>
						<th style="width:8%">修改时间</th>
						<th style="width:14%">操作</th>
					</tr>
					<#list pageInfo.objects as advertisement>
					  <tr>
					     <td>${advertisement.id}</td>			
					     <td>${advertisement.ad_desc}</td>						
					     <td><img src="${imageContextPath}${advertisement.ad_pic_url}" style="width:200px;height:150px;"/></td>
					     <td>${advertisement.ad_link_url}</td>
					     <td>${advertisement.ad_position.value}</td>	
					     <td>${advertisement.start_show_time?datetime}</td>	
					     <td>${advertisement.end_show_time?datetime}</td>	
					     <td>${advertisement.status.value}</td>
					     <td>${advertisement.create_time?datetime}</td>
					     <td>${advertisement.update_time?datetime}</td>
					     <td>
					     	<div class="table-fun">
					     		<a href="${contextPath}/admin/ad?adId=${advertisement.id}">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteAdvertisement(${advertisement.id});">删除</a>
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
<script src="${contextPath}/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/admin/js/page.js" charset="utf-8"></script>
<script language="javascript" src="${contextPath}/admin/js/My97DatePicker/wdatePicker.js"></script>
<script>
var contextPath="${contextPath}";
function deleteAdvertisement(adId){
	var txt = "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		window.location.href=contextPath+"/admin/deleteAdvertisement?adId="+adId;
	}})
}
</script>
</body>
</html>