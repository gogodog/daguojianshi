<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js?v=${staticVersion}"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">反馈管理</a><a href="">后台反馈管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">反馈列表</h3>
				<div class="public-content-right fr">
			</div>
			</div>
			<div class="public-content-cont">
			<form id="selectForm" action="${contextPath}/admin/afb/feedBackList" method="post">
			<input type="hidden" name="currentPage">
		    <p style="margin-bottom:10px">
		        <label>回馈状态:</label>
		           <select name="isHaveReply">
		              <option value="">全部</option>
		              <option value="false" <#if condition.isHaveReply?? && condition.isHaveReply == false>selected</#if>>未回馈</option>
		              <option value="true" <#if condition.isHaveReply?? && condition.isHaveReply == true>selected</#if>>已回馈</option>
		           </select>
		        <label>反馈时间:</label><input type="text" name="time_from" value="<#if condition.timeFrom??>${condition.timeFrom?date}</#if>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px"/> 
		        - <input type="text" value="<#if condition.timeTo??>${condition.timeTo?date}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px" name="time_to"/>&nbsp;&nbsp;
		        <input type="button" value="查询" name="conditionButton" class="select-btn"/>
		    </p>
		    </form>
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:10%">反馈时间</th>
						<th style="width:8%">反馈人</th>
						<th style="width:10%">反馈内容</th>
						<th style="width:10%">是否已回馈</th>
						<th style="width:10%">操作</th>
					</tr>
					<#list pageInfo.objects as fd>
					  <tr>
					     <td>${fd.feedBack.create_time?datetime}</td>
					     <td>${fd.adminUser.username}</td>						
					     <td>${fd.feedBack.message}</td>
					     <td><#if fd.feedBack.haveReply?? && fd.feedBack.haveReply == true>已回馈<#else>未回馈</#if></td>
					     <td>
					       <div class="table-fun">
				     		  <a href="/admin/afb/detail?relatedId=${fd.feedBack.id}">回复</a>
				     	   </div>
					     </td>
				     </tr>
				   </#list>
				</table>
				<#include "/admin/common/page.ftl">
			</div>
		</div>
	</div>
</body>
<script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js?v=${staticVersion}"></script>
<script src="/admin/js/page.js?v=${staticVersion}" charset="utf-8"></script>
<script>
var contextPath="${contextPath}";
</script>
</html>