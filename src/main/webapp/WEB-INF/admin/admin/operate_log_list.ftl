<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">后台用户管理</a><a href="javascript:void(0)">操作日志</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">操作日志</h3>
			</div>
			<div class="public-content-cont">
			   <form id="selectForm" action="${contextPath}/admin/oprtlg/list" method="post">
		        <input type="hidden" name="currentPage">
		             <p style="margin-bottom:10px">
		               <label>用户名:</label><input type="text" name="username" value="${(condition.username)!''}"/>&nbsp;&nbsp;
		               <label>操作时间:</label><input type="text" name="createTimeFrom" value="<#if condition.createTimeFrom??>${condition.createTimeFrom?date}</#if>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px"/> 
				        - <input type="text" value="<#if condition.createTimeTo??>${condition.createTimeTo?date}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px" name="createTimeTo"/>&nbsp;&nbsp;
		               <label>操作类型:</label><select name="operateTypes" multiple="multiple"><option value="">全部</option><#list operateTypes as operateType><option value="${operateType}">${operateType.value}</option></#list></select>&nbsp;&nbsp;
		               <label>是否成功:</label><select name="isSuccess"><option value="-1">全部</option><option value="1" <#if condition.isSuccess==1>selected</#if>>成功</option><option value="0" <#if condition.isSuccess==0>selected</#if>>失败</option></select>&nbsp;&nbsp;
		               <input type="button" value="查询" name="conditionButton" class="select-btn"/>
		             </p>
		        </form>
			    <table class="public-cont-table">
					<tr>
					    <th style="width:15%">操作时间</th>
						<th style="width:10%">操作类型</th>
						<th style="width:15%">操作描述</th>
						<th style="width:15%">操作人</th>
						<th style="width:10%">姓名</th>
						<th style="width:10%">ip</th>
						<th style="width:15%">是否执行成功</th>
						<th style="width:10%">操作</th>
					</tr>
					<#list pageInfo.objects as result>
					  <tr>
					     <td>${result.operateLog.operate_time?datetime}</td>	
					     <td>${result.operateLog.operate_type.value}</td>			
					     <td>${result.operateLog.operate_desc}</td>
					     <td>${result.username}</td>	
					     <td>${result.realname}</td>	
					     <td>${result.operateLog.ip}</td>	
					     <td><#if result.operateLog.isSuccess == 1>成功<#else>失败</#if></td>	
					     <td>
					       <div class="table-fun-1">
				     		<a href="/admin/oprtlg/detail?id=${result.operateLog.id}">查看详情</a>
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
<script>
var contextPath="${contextPath}";
</script>
</body>
</html>