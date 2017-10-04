<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="${contextPath}/admin/css/xcConfirm.css"/>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">后台用户管理</a><a href="javascript:void(0)">角色列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">角色列表</h3>
				<div class="public-content-right fr">
				  <a href="${contextPath}/admin/admin/role"
				     style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加角色</a>
			    </div>
			</div>
			<div class="public-content-cont">
			    <table class="public-cont-table">
					<tr>
					    <th style="width:5%">角色id</th>
						<th style="width:10%">角色名称</th>
						<th style="width:10%">创建时间</th>
						<th style="width:10%">修改时间</th>
						<th style="width:14%">操作</th>
					</tr>
					<#list list as role>
					  <tr>
					     <td>${role.id}</td>			
					     <td>${role.role_name}</td>
					     <td>${role.create_time?datetime}</td>	
					     <td>${role.update_time?datetime}</td>	
					     <td>
					     	<div class="table-fun">
					     		<a href="${contextPath}/admin/admin/role?id=${role.id}">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteRole('${role.id}');">删除</a>
					     	</div>
					     </td>
				     </tr>
				   </#list>
				</table>
			</div>
		</div>
	</div>
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script src="${contextPath}/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/admin/js/page.js" charset="utf-8"></script>
<script language="javascript" src="${contextPath}/admin/js/My97DatePicker/wdatePicker.js"></script>
<script>
var contextPath="${contextPath}";
function deleteRole(aId){
	var txt = "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		$.ajax({
			async:false,
			data:{id:aId},
			dataType: "json",
			url:contextPath+"/admin/admin/deleteRole",
			type:"POST",
			success:function(data) {
	            if(data.error){
	            	alert(data.errorMessage);
	            }else{
	            	alert('删除成功');
	            	window.location.href=contextPath+"/admin/admin/roleList";
	            }
	        }, 
	        error:function(){
	            console.log("服务器繁忙...");
	        }
		});
	}})
}
</script>
</body>
</html>