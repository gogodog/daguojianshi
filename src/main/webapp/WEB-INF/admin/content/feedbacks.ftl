<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">反馈管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">反馈列表</h3>
				<div class="public-content-right fr">
			</div>
			</div>
			<div class="public-content-cont">
			<form id="selectForm" action="${contextPath}/admin/fb/feedBackList" method="post">
			<input type="hidden" name="currentPage">
		    <p style="margin-bottom:10px">
		        <label>文章id:</label><input type="text" name="articlescrap_id" value="${(condition.articlescrap_id)!''}"/>&nbsp;&nbsp;
		        <label>反馈时间:</label><input type="text" name="time_from" value="<#if condition.timeFrom??>${condition.timeFrom?date}</#if>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px"/> 
		        - <input type="text" value="<#if condition.timeTo??>${condition.timeTo?date}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px" name="time_to"/>&nbsp;&nbsp;
		        <input type="button" value="查询" name="conditionButton" class="select-btn"/>
		    </p>
		    </form>
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:14%">文章id</th>
						<th style="width:16%">文章标题</th>
						<th style="width:10%">反馈时间</th>
						<th style="width:8%">反馈人</th>
						<th style="width:10%">邮箱</th>
						<th style="width:7%">评判级别</th>
						<th style="width:7%">疑问程度</th>
						<th style="width:28%">评论内容</th>
					</tr>
					<#list pageInfo.objects as fd>
					  <tr>
					     <td>${fd.feedBack.articlescrap_id}</td>
					     <td>${fd.title}</td>						
					     <td>${fd.feedBack.create_time?datetime}</td>
					     <td>${fd.feedBack.uname}</td>
					     <td>${fd.feedBack.email}</td>
					     <td>${fd.feedBack.judge_level.value}</td>	
					     <td>${fd.feedBack.doubt_level}</td>
					     <td>${fd.feedBack.judge_message}</td>
				     </tr>
				   </#list>
				</table>
				<#include "/admin/common/page.ftl">
			</div>
		</div>
	</div>
</body>
<script language="javascript" src="${contextPath}/admin/js/My97DatePicker/wdatePicker.js"></script>
<script src="${contextPath}/admin/js/page.js" charset="utf-8"></script>
<script>
var contextPath="${contextPath}";
</script>
</html>