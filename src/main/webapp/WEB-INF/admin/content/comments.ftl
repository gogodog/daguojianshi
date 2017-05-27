<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="${contextPath}/admin/css/xcConfirm.css"/>
<#include "/admin/common/head_title.ftl">
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
</head>
<body marginwidth="0" marginheight="0">
<div class="container">
<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">评论管理</a></div>
<div class="public-content">
	<div class="public-content-header">
	  <h3 style="display: inline-block;">评论管理</h3>
	</div>
	<div class="public-content-cont">
	  <form id="selectForm" action="${contextPath}/admin/articlescrapList" method="post">
	    <input type="hidden" name="currentPage">
	  </form>
	  <table class="public-cont-table">
		<tr>
		    <th style="width:15%">评论id</th>
			<th style="width:7%">评论人</th>
			<th style="width:10%">ip地址</th>
			<th style="width:8%">评论时间</th>
			<th style="width:7%">是否展示</th>
			<th style="width:15%">父级评论id</th>
			<th style="width:18%">评论内容</th>
			<th style="width:10%">评论描述</th>
			<th style="width:10%">操作</th>
		</tr>
		<#list pageInfo.objects as comments>
		  <tr>
		     <td>${comments.id}</td>			
		     <td>${comments.comment_name}</td>						
		     <td>${comments.ip_address}</td>
		     <td>${comments.comment_time?datetime}</td>
		     <td><#if comments.is_show == true>是<#else>否</#if></td>	
		     <td>${comments.parent_id}</td>
		     <td>${comments.comment}</td>
		     <td>${comments.c_desc}</td>
		     <td>
		     	<div class="table-fun">
		     		<a href="javascript:void(0);" onclick="update('${comments.id}',${comments.articlescrap_id});">修改</a>
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
<script src="${contextPath}/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
<script>
var contextPath="${contextPath}";
function update(id,articlescrap_id){
	var txt = "评论描述:&nbsp;&nbsp;<input type='text' style='width:180px;' id='desc' /><br>是否展示:&nbsp;&nbsp;<input type='checkbox' id='is_show'/>";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.custom,{onOk:function(){
		var desc=$("#desc").val();
		var is_show=$('#is_show').is(':checked');
		window.location.href=contextPath+"/admin/updateComments?id="+id+"&desc="+desc+"&isShow="+is_show+"&articlescrapId="+articlescrap_id;
	}})
}
</script>
</html>