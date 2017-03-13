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
				<div class="public-content-right fr">
				<a href="${contextPath}/admin/articlescrap" 
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加文章</a>
			</div>
			</div>
			
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:12%">文章标题</th>
						<th style="width:20%">文章内容</th>
						<th style="width:10%">文章展示时间</th>
						<th style="width:8%">分类</th>
						<th style="width:8%">状态</th>
						<th style="width:8%">作者</th>
						<th style="width:8%">创建时间</th>
						<th style="width:8%">修改时间</th>
						<th style="width:14%">操作</th>
					</tr>
					<#list articlescrapList as articlescrap>
					  <tr>
					     <td>${articlescrap.title}</td>						
					     <td>${articlescrap.content}</td>
					     <td>${articlescrap.show_time?date}</td>
					     <td>${articlescrap.type.value}</td>	
					     <td>${articlescrap.status.value}</td>
					     <td>${articlescrap.author}</td>
					     <td>${articlescrap.create_time?date}</td>
					     <td>${articlescrap.update_time?date}</td>
					     <td>
					     	<div class="table-fun">
					     		<a href="${contextPath}/admin/articlescrap?articlescrapId=${articlescrap.id}">修改</a>
					     		<a href="${contextPath}/admin/deleteArticlescrap?articlescrapId=${articlescrap.id}">删除</a>
					     	</div>
					     </td>
				     </tr>
				   </#list>
				</table>
			</div>
		</div>
	</div>
</body>
</html>