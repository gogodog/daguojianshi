<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="/admin/css/xcConfirm.css"/>
<script src="/admin/js/jquery-1.11.1.min.js"></script>
<script src="/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">轮播图管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">轮播图列表</h3>
				<div class="public-content-right fr">
				<a href="/admin/cul/carousel" 
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加轮播图</a>
			</div>
			</div>
			
			<div class="public-content-cont">
			<form id="selectForm" action="${contextPath}/admin/cul/carouselList" method="post">
		      <p style="margin-bottom:10px">
		        <label>位置:</label><select name="position"><#list positions as position>
	              <option <#if carousel.position?? && carousel.position == position>selected</#if> value="${position}">${position.value}</option>
	           </#list></select>&nbsp;&nbsp;
		        <label>状态:</label><select name="status"><option value="">全部</option><#list upDownStatus as status><option value="${status}" <#if carousel.status.key=="${status.key}">selected</#if>>${status.value}</option></#list></select>&nbsp;&nbsp;
		        <input type="submit" value="查询" name="conditionButton" class="select-btn">
		      </p>
		   </form>
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
					     		<a href="/admin/cul/carousel?carouselId=${carousel.id}">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteCarousel('${carousel.id}','${carousel.position}');">删除</a>
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
function deleteCarousel(carouselId,position){
	var txt=  "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		window.location.href=contextPath+"/admin/cul/deleteCarousel?id="+carouselId+"&position="+position;
	}})
}
</script>
</html>