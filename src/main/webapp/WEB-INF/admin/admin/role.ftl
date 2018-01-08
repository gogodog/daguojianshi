<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js?v=${staticVersion}"></script>
<script src="/admin/js/support-fileupload.js?v=${staticVersion}"></script>
<script src="/admin/js/ajaxfileupload.js?v=${staticVersion}"></script>
<script src="/admin/js/validation/jquery.validate.js?v=${staticVersion}"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">后台用户管理</a>><a href="">角色管理</a>><a href="/admin/admin/role">添加角色</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加角色</h3>
			</div>
			<div class="public-content-cont">
			    <fieldset>    
			    <input type="hidden" name="roleId" value="${(dto.role.id)!''}">
				<div class="form-group">
					<label for="">角色名称</label>
					<input class="form-input-txt" type="text" name="roleName" value="${(dto.role.role_name)!''}" maxlength="50"/>
				</div>
				<div class="form-group">
				    <label for="">权限</label>
				    <#list authorityList as ra>
				       <input style="margin-top:9px" type="checkbox" name="authorityIds" value="${ra.id}" 
				    		   <#list dto.authoritys as dto_ra>
				                  <#if dto_ra.id == ra.id> checked <#break> </#if>
				               </#list>
				       />${ra.authority_url}&nbsp;&nbsp;${ra.authority_name}<br>
				    </#list>
			    </div>
				<div class="form-group" style="margin-left:150px;">
					<input type="button" class="sub-btn" value="提  交" onclick="validateAndSubmit();"/>
					<input type="reset" class="sub-btn" value="重  置" />
				</div>
				</fieldset>
			</div>
		</div>
	</div>
</body>
<script>
function validateAndSubmit(){
	var contextPath = '${contextPath}';
	var roleName = $("input[name='roleName']").val();
	if(roleName == null || roleName.length == 0){
		alert('角色名称不能为空');
		return;
	}
	var roleId = $("input[name='roleId']").val();
	var authorityIds = "";
	$("input[name='authorityIds']:checked").each(function(){
		authorityIds += $(this).val()+","; 
	});
	$.ajax({
		async:false,
		data:{roleName:roleName,roleId:roleId,authorityIds:authorityIds},
		dataType: "json",
		url:contextPath+"/admin/admin/saveOrUpdateRole",
		type:"POST",
		success:function(data) {
            if(data.error){
            	if(data.errorCode!='SUCCESS'){
            		alert(data.errorMessage);
            	}
            }else{
            	window.location.href=contextPath+"/admin/admin/roleList";
            }
        }, 
        error:function(){
            console.log("服务器繁忙...");
        }
	});
}
</script>
</html>