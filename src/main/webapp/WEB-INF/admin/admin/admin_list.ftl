<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="${contextPath}/admin/css/xcConfirm.css"/>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">后台用户管理</a><a href="javascript:void(0)">用户列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">用户列表</h3>
			</div>
			<div class="public-content-cont">
			   <form id="selectForm" action="${contextPath}/admin/adnur/adminList" method="post">
		        <input type="hidden" name="currentPage">
		             <p style="margin-bottom:10px">
		               <label>用户code:</label><input type="text" name="user_code" value="${(condition.user_code)!''}"/>&nbsp;&nbsp;
		               <label>用户名:</label><input type="text" name="username" value="${(condition.username)!''}"/>&nbsp;&nbsp;
		               <label>角色:</label><select name="role_id"><option value="">全部</option><#list roleList as role><option value="${role.id}" <#if condition.role_id=="${role.id}">selected</#if>>${role.role_name}</option></#list></select>&nbsp;&nbsp;
		               <label>状态:</label><select name="status"><option value="">全部</option><option value="DOWN" <#if condition.status.key==0>selected</#if>>禁用</option><option value="UP" <#if condition.status.key==1>selected</#if>>启用</option></select>&nbsp;&nbsp;
		               <input type="button" value="查询" name="conditionButton" class="select-btn"/>
		             </p>
		        </form>
			    <table class="public-cont-table">
					<tr>
					    <th style="width:10%">用户code</th>
						<th style="width:10%">用户名</th>
						<th style="width:10%">来源</th>
						<th style="width:15%">邮件</th>
						<th style="width:10%">电话</th>
						<th style="width:10%">姓名</th>
						<th style="width:15%">创建时间</th>
						<th style="width:10%">操作</th>
					</tr>
					<#list pageinfo.objects as result>
					  <tr title="角色:${result.role_name},性别:<#if result.sex == 1>男<#elseif result.sex == 2>女</#if>,年龄:${result.age},地址:${result.address}">
					     <td>${result.user_code}</td>			
					     <td>${result.username}</td>
					     <td>${result.organization}</td>	
					     <td>${result.email}</td>	
					     <td>${result.mobile}</td>	
					     <td>${result.real_name}</td>	
					     <td>${result.create_time?datetime}</td>	
					     <td>
					     	<div class="table-fun">
					     		<a href="javascript:void(0)" onclick="updateStatus('${result.id}','${result.status.key}');"><#if result.status.key=="0">启用<#elseif result.status.key=="1">禁用</#if></a>
					     	</div>
					     </td>
				     </tr>
				   </#list>
				</table>
				<#include "/admin/common/page.ftl">
			</div>
		</div>
	</div>
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script src="${contextPath}/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/admin/js/page.js" charset="utf-8"></script>
<script language="javascript" src="${contextPath}/admin/js/My97DatePicker/wdatePicker.js"></script>
<script>
var contextPath="${contextPath}";
function updateStatus(uid,status){
	var operate = "";
	var operate_status = "";
	if(status == '1'){
		operate = "禁用";
		operate_status = 0;
	}else if(status == '0'){
		operate = "启用";
		operate_status = 1;
	}
	var txt = "您确定要"+operate+"这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		$.ajax({
			async:false,
			data:{uid:uid,status:operate_status},
			dataType: "json",
			url:contextPath+"/admin/adnur/updateAdminStatus",
			type:"POST",
			success:function(data) {
	            if(data.error){
	            	alert(data.errorMessage);
	            }else{
	            	alert(operate+'成功');
	            	window.location.href=contextPath+"/admin/adnur/adminList";
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