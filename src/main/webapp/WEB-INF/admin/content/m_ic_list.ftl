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
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">首页配置管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">首页配置管理列表</h3>
				<div class="public-content-right fr">
				<a href="/admin/idxcfg/indexConfig" 
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加配置</a>
			</div>
			</div>
			
			<div class="public-content-cont">
			<form id="selectForm" action="${contextPath}/admin/idxcfg/list" method="post">
		      <p style="margin-bottom:10px">
		        <label>位置:</label><select name="type"><#list types as type>
	              <option <#if config.type?? && config.type == type>selected</#if> value="${type}">${type.value}</option>
	           </#list></select>&nbsp;&nbsp;
		        <label>状态:</label><select name="status"><option value="">全部</option><#list upDownStatus as status><option value="${status}" <#if config.status.key=="${status.key}">selected</#if>>${status.value}</option></#list></select>&nbsp;&nbsp;
		        <input type="submit" value="查询" name="conditionButton" class="select-btn">
		      </p>
		   </form>
				<table class="public-cont-table">
					<tr>
						<th style="width:8%">类型</th>
						<th style="width:8%">位置</th>
						<th style="width:14%">原文标题</th>
						<th style="width:8%">排序</th>
						<th style="width:8%">状态</th>
						<th style="width:14%">标题</th>
						<th style="width:20%">精简内容</th>
						<th style="width:20%">操作</th>
					</tr>
					<#list list as dto>
					  <tr>
					     <td>${dto.indexConfig.type.value}</td>
					     <td>${dto.indexConfig.position}</td>						
					     <td>${dto.articlescrap.title}</td>
					     <td>${dto.indexConfig.sort}</td>
					     <td>${dto.indexConfig.status.value}</td>
					     <td>${dto.indexConfig.title}</td>	
					     <td>${dto.indexConfig.sub_content}</td>
					     <td>
					     	<div class="table-fun">
					     		<a href="/admin/idxcfg/indexConfig?id=${dto.indexConfig.id}">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteIndexConfig('${dto.indexConfig.id}','${dto.indexConfig.type}');">删除</a>
					     	</div>
					     </td>
				     </tr>
				   </#list>
				</table>
			</div>
		</div>
	</div>
	<script>
	var contextPath="${contextPath}";
	function deleteIndexConfig(id,type){
		var txt=  "您确定要删除这条数据吗？";
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
			window.location.href=contextPath+"/admin/idxcfg/delete?id="+id+"&type="+type;
		}})
	}
	</script>
</body>
</html>