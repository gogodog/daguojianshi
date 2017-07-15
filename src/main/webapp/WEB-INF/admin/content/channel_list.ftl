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
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">频道管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">频道列表</h3>
				<div class="public-content-right fr">
				<a href="javascript:void(0)" onclick="showChannelPop();"
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加频道</a>
			</div>
			</div>
			
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:10%">频道名称</th>
						<th style="width:10%">状态</th>
						<th style="width:10%">频道排序</th>
						<th style="width:20%">创建时间</th>
						<th style="width:20%">修改时间</th>
						<th style="width:30%">操作</th>
					</tr>
					<#list channels as channel>
					  <tr>
					     <td>${channel.c_name}</td>
					     <td>${channel.status.value}</td>						
					     <td>${channel.sort}</td>
					     <td>${channel.create_time?datetime}</td>
					     <td>${channel.update_time?datetime}</td>
					     <td>
					     	<div class="table-fun">
					     		<a href="javascript:void(0)">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteChannel(${channel.id});">删除</a>
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
function deleteChannel(channelId){
	var txt=  "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		window.location.href=contextPath+"/admin/channel/delete?channel_id="+channelId;
	}})
}

function showChannelPop(channelId){
	var txt = "频道名称：<input type=\"text\" class=\"inputBox\" style=\"width:120px;\"><br>"
			+ "排序：<input type=\"text\" class=\"inputBox\" style=\"width:120px;\"><br>"
	        + "状态：<input type=\"checkbox\"><br>";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		window.location.href=contextPath+"/admin/channel/delete?channel_id="+channelId;
	}})
}
</script>
</html>