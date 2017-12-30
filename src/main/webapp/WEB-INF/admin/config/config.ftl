<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js"></script>
<script src="/admin/js/validation/jquery.validate.js"></script>
<link href="/admin/plugins/cropper/css/cropper.min.css" rel="stylesheet">
<link href="/admin/plugins/cropper/css/bootstrap.min.css" rel="stylesheet">
<script src="/admin/plugins/cropper/js/bootstrap.min.js"></script>
<script src="/admin/plugins/cropper/js/cropper.min.js"></script>
<script src="/admin/plugins/cropper/js/main.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">配置管理</a>><a href="">参数配置管理</a>><a href="">添加配置</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加配置</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/config/saveConfig" method="post" id="configForm">
			    <fieldset>    
			    <input type="hidden" name="id" value="${(config.id)!''}">
				<div class="form-group">
					<label for="">描述</label>
					<input class="form-input-txt" type="text" name="c_desc" value="${(config.c_desc)!''}" maxlength="50" />
				</div>
				<div class="form-group">
				    <label for="">key</label>
				    <input class="form-input-txt" type="text" name="c_key" value="${(config.c_key)!''}" maxlength="32" <#if config.id??>readOnly</#if> />
			    </div>
			    <div class="form-group">
			        <label for="">value</label>
			        <input class="form-input-txt" type="text" name="c_value" value='${config.c_value}'  maxlength="127" />
		        </div>
				<div class="form-group" style="margin-left:150px;">
					<input type="submit" class="sub-btn" value="提  交" />
					<input type="reset" class="sub-btn" value="重  置" />
				</div>
				</fieldset>
				</form>
              </div>
			</div>
		</div>
	</div>
</body>
<script>
$().ready(function() {
	// 在键盘按下并释放及提交后验证提交表单
	  $("#configForm").validate({
	    rules: {
		  c_desc: {
	        required: true,
	        rangelength:[1,50]
	      },
		  c_key: {
	        required: true,
	        rangelength:[1,32]
	      },
		  c_value: {
		    required: true,
		    rangelength:[1,127]
		  }
	    },
	    messages: {
	      c_desc: {
	        required: "请输入描述",
	        rangelength: "长度在1-50之间"
	      },
	      c_key: {
	    	required: "请输入key",
	    	rangelength: "长度在1-32之间"
	      },
	      c_value: {
		    required: "请输入value",
		    rangelength: "长度在1-127之间"
		  }
	    },
	    submitHandler:function(form){
	    	var id = $("input[name='id']").val();
	    	if(id==''||id==null){
	    		$("#configForm").attr("action","/admin/config/save");
	    	}else{
	    		$("#configForm").attr("action","/admin/config/update");
	    	}
            form.submit();
        }    
	});
});
</script>
</html>