<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js"></script>
<script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js"></script>
<script src="/admin/js/support-fileupload.js"></script>
<script src="/admin/js/ajaxfileupload.js"></script>
<script src="/admin/js/validation/jquery.validate.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">后台用户管理</a>><a href="">用户管理</a>><a href="javascript:void(0)">用户详情</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>用户详情</h3>
			</div>
			<div class="public-content-cont">
		    	<label for="">用户code：</label>${adminUser.user_code}<br><br>
		    	<label for="">用户名：</label>${adminUser.username}<br><br>
		    	<label for="">姓名：</label>${adminUserInfo.real_name}<br><br>
		    	<label for="">性别：</label><#if adminUserInfo.sex==1>男<#elseif adminUserInfo.sex==2>女<else>其他</#if><br><br>
		    	<label for="">年龄：</label>${adminUserInfo.age}<br><br>
		    	<label for="">组织：</label>${adminUserInfo.organization}<br><br>
		    	<label for="">邮件：</label>${adminUserInfo.email}<br><br>
		    	<label for="">电话：</label>${adminUserInfo.mobile}<br><br>
		    	<label for="">地址：</label>${adminUserInfo.address}<br><br>
		    	<label for="">头像：</label><img src="${adminUser.headimgurl}" style="width:100px;height:100px"/><br><br>
		    	<label for="">创建时间：</label>${adminUserInfo.create_time?datetime}<br><br>
		    	<label for="">修改时间：</label>${adminUserInfo.update_time?datetime}<br><br>
		    	<span><label for="">角色：</label>
                   <select id="role">
                     <#list allRole as roles>
                       <option value="${roles.id}" <#if roles.id==role.id>selected</#if>>${roles.role_name}</option>
                     </#list>
                   </select>
                   <input type="button" class="sub-btn" value="修改角色" onclick="updateRole('${adminUser.id}');"/><br><br></span>
			</div>
		</div>
	</div>
	<script>
	   var contextPath = '${contextPath}';
       function updateRole(uid){
    	  var role = $("#role").val();
    	  $.ajax({
  			async:false,
  			data:{uid:uid,role:role},
  			dataType: "json",
  			url:contextPath+"/admin/adnur/updateAdminRole",
  			type:"POST",
  			success:function(data) {
  	            if(data.error){
  	            	alert(data.errorMessage);
  	            }else{
  	            	alert('角色修改成功');
  	            }
  	        }, 
  	        error:function(){
  	            console.log("服务器繁忙...");
  	        }
  		});
       }
	</script>
</body>
</html>