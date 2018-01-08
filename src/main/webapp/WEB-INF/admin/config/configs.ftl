<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="/admin/css/xcConfirm.css?v=${staticVersion}"/>
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js?v=${staticVersion}"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">配置管理</a><a href="">配置列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">配置列表</h3>
			  <div class="public-content-right fr">
			     <a href="/admin/config/detail" 
			        style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加配置</a>
			</div>
			</div>
			<div class="public-content-cont">
			<form id="selectForm" action="${contextPath}/admin/config/list" method="post">
			<input type="hidden" name="currentPage">
		    <p style="margin-bottom:10px">
		        <label>key:</label><input type="text" name="key" value="${(condition.key)!''}"/>&nbsp;&nbsp;
		        <label>描述:</label><input type="text" name="desc" value="${(condition.desc)!''}"/>&nbsp;&nbsp;
		        <input type="button" value="查询" name="conditionButton" class="select-btn"/>
		    </p>
		    </form>
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:15%">描述</th>
						<th style="width:15%">key</th>
						<th style="width:20%">value</th>
						<th style="width:15%">创建时间</th>
						<th style="width:15%">修改时间</th>
						<th style="width:20%">操作</th>
					</tr>
					<#list list as config>
					  <tr>
					     <td>${config.c_desc}</td>	
					     <td>${config.c_key}</td>						
					     <td>${config.c_value}</td>
					     <td>${config.create_time?datetime}</td>
					     <td>${config.update_time?datetime}</td>
					     <td>
					       <div class="table-fun">
				     		  <a href="/admin/config/detail?id=${config.id}">修改</a>
				     		  <a href="javascript:void(0)" onclick="deleteById('${config.id}');">删除</a>
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
<script src="/admin/js/page.js?v=${staticVersion}" charset="utf-8"></script>
<script src="/admin/js/confirm/xcConfirm.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
<script>
var contextPath="${contextPath}";
function deleteById(id){
	var txt = "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		window.location.href=contextPath+"/admin/config/delete?id="+id;
	}})
}
</script>
</html>