<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="${contextPath}/admin/css/xcConfirm.css"/>
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script src="${contextPath}/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">轮播图管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">轮播图列表</h3>
				<div class="public-content-right fr">
				<a href="${contextPath}/admin/carousel" 
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加轮播图</a>
			</div>
			</div>
			
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:3%">ID</th>
						<th style="width:12%">描述</th>
						<th style="width:20%">链接</th>
						<th style="width:15%">地址</th>
						<th style="width:16%">图片</th>
						<th style="width:5%">排序</th>
						<th style="width:5%">状态</th>
						<th style="width:14%">操作</th>
					</tr>
					<#list carouselList as carousel>
					  <tr>
					     <td>${carousel.id}</td>
					     <td>${carousel.image_desc}</td>						
					     <td>${carousel.link_url}</td>
					     <td>${carousel.image_url}</td>
					     <td><img class="thumb" src="${carousel.image_url}" /></td>
					     <td>${carousel.sort}</td>	
					     <td>${carousel.status.value}</td>
					     <td>
					     	<div class="table-fun">
					     		<a href="${contextPath}/admin/carousel?carouselId=${carousel.id}">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteCarousel(${carousel.id});">删除</a>
					     	</div>
					     </td>
				     </tr>
				   </#list>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
var contextPath="${contextPath}";
function deleteCarousel(carouselId){
	var txt=  "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		window.location.href=contextPath+"/admin/deleteCarousel?carouselId="+carouselId;
	}})
}
</script>
</html>