<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">文章推荐管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">文章推荐列表</h3>
				<div class="public-content-right fr">
				<a href="${contextPath}/admin/recommedArticlescrap" 
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加推荐</a>
			</div>
			</div>
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:5%">ID</th>
						<th style="width:20%">文章标题</th>
						<th style="width:40%">文章内容</th>
						<th style="width:5%">排序</th>
						<th style="width:5%">状态</th>
						<th style="width:25%">操作</th>
					</tr>
					<#list recommedArticlescrapList as recommedArticlescrap>
					  <tr>
					     <td>${recommedArticlescrap.id}</td>
					     <td>${recommedArticlescrap.title}</td>		
					     <td>${recommedArticlescrap.content}</td>		
					     <td>${recommedArticlescrap.sort}</td>	
					     <td>${recommedArticlescrap.status.value}</td>
					     <td>
					     	<div class="table-fun-1">
					     	    <a href="${contextPath}/admin/articlescrap?articlescrapId=${recommedArticlescrap.articlescrap_id}">查看文章</a>
					     		<a href="${contextPath}/admin/deleteRecommedArticlescrap?recommedArticlescrapId=${recommedArticlescrap.id}">删除</a>
					     		<a href="${contextPath}/admin/updateStatus?recommedArticlescrapId=${recommedArticlescrap.id}">
                                   <#if recommedArticlescrap.status.key == 1>
                                      下架
                                   </#if>
                                   <#if recommedArticlescrap.status.key == 0>
                                      上架
                                   </#if>
					     		</a>
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