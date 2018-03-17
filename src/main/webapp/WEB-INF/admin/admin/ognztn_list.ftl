<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">后台用户管理</a><a href="javascript:void(0)">组织管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">新增组织</h3>
			  <div class="public-content-right fr">
			    <a href="/admin/ognztn/detail"
			     style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">新增组织</a>
		       </div>
			</div>
			<div class="public-content-cont">
			   <form id="selectForm" action="${contextPath}/admin/ognztn/list" method="post">
		        <input type="hidden" name="currentPage">
		             <p style="margin-bottom:10px">
		               <label>组织名:</label><input type="text" name="oname" value="${(condition.oname)!''}"/>&nbsp;&nbsp;
		               <input type="button" value="查询" name="conditionButton" class="select-btn"/>
		             </p>
		        </form>
			    <table class="public-cont-table">
					<tr>
					    <th style="width:15%">组织名</th>
						<th style="width:10%">代理人</th>
						<th style="width:15%">链接</th>
						<th style="width:30%">简介</th>
						<th style="width:10%">时间</th>
						<th style="width:20%">操作</th>
					</tr>
					<#list pageinfo.objects as result>
					  <tr>
					     <td>${result.organization.oname}</td>	
					     <td>${result.username}</td>			
					     <td>${result.organization.link}</td>
					     <td>${result.organization.summary}</td>	
					     <td>创建时间：${result.organization.create_time?datetime}<br>
					         修改时间：${result.organization.update_time?datetime}    
					     </td>	
					     <td>
					       <div class="table-fun-1">
				     		 <a href="/admin/ognztn/detail?id=${result.organization.id}">修改</a>
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