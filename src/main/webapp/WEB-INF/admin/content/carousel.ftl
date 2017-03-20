<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script src="${contextPath}/admin/js/support-fileupload.js"></script>
<script src="${contextPath}/admin/js/ajaxfileupload.js"></script>
<script src="${contextPath}/admin/js/validation/jquery.validate.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">轮播图管理</a>><a href="${contextPath}/admin/carousel">添加轮播图</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加轮播</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/saveCarousel" method="post" enctype="multipart/form-data" id="carouselForm">
			    <fieldset>    
			    <input type="hidden" name="id" value="${(carousel.id)!''}">
			    <input type="hidden" name="status" value="${(carousel.status)!'DOWN'}">
			    <input type="hidden" name="image_url" value="${(carousel.image_url)!''}">
				<div class="form-group">
					<label for="">排序</label>
					<input class="form-input-txt" type="text" name="sort" value="${(carousel.sort)!''}" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
				</div>
				<div class="form-group">
					<label for="">跳转链接</label>
					<input class="form-input-txt" type="text" name="link_url" value="${(carousel.link_url)!''}" maxlength="255"/>
				</div>
				<div class="form-group">
					<label for="">描述</label>
					<input class="form-input-txt" type="text" name="image_desc" value="${(carousel.image_desc)!''}" maxlength="255" />
				</div>
				<div class="form-group">
					<label for="">图片</label>
					<img src="<#if carousel.image_url??>${imageContextPath}${carousel.image_url}</#if>" id="showImage" name="showImage" style="width:200px;height:200px;<#if carousel.image_url??>display:block;<#else>display:none;</#if>">
					<div class="file"><input type="file" class="form-input-file" id="uploadImage" name="uploadImage"/>选择文件</div>
					<div class="file"><input type="button" class="form-input-file" id="buttonUpload" onClick="return ajaxFileUpload();">上传</div>
				</div>
				<div class="form-group">
				    <label for="">是否上架：</label>
				    <input style="margin-top:9px" type="checkbox" id="status" value="" <#if carousel.status.key?? && carousel.status.key==1>checked</#if> />
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
</body>
<script>
$("#status").click(function(){
	if($('#status').is(':checked')) {
		   $("input[name='status']").val("UP");
	}else{
		$("input[name='status']").val("DOWN");
	}
});

function ajaxFileUpload()
{
    var contextPath="${contextPath}";
    var imageContextPath="${imageContextPath}";
    var uploadFileName="carousel";
    $.ajaxFileUpload
    (
        {
        	async:false,
            url:contextPath+'/admin/ajaxUpload?imagePath='+uploadFileName,//这个是要提交到上传的php程序文件
            secureuri:false,
            fileElementId:'uploadImage',//这里是你文件上传input框的id
            dataType: 'json',
            success: function (result)
            {
                if(typeof(result.error) != 'undefined')
                {
                    if(result.error != '')
                    {
                        alert(result.errorMessage);//如有错误则弹出错误
                    }else
                    {
                    	var accessPath=imageContextPath+result.imageUrl;
                        $("#showImage").attr("src",accessPath);
                        $("input[name='image_url']").val(result.imageUrl);
                        $("#showImage").show();
                    }
                }
            },
            error: function (result, status, e)
            {
                alert(e);
            }
        }
    )
}

$().ready(function() {
	// 在键盘按下并释放及提交后验证提交表单
	  $("#carouselForm").validate({
	    rules: {
	      sort: {
	        required: true,
	        digits:true
	      },
	      link_url: {
	        required: true,
	      }
	    },
	    messages: {
	      sort: {
	        required: "请输入排序",
	        digits: "请输入整数"
	      },
	      link_url: {
	    	required: "请输入跳转链接"
	      }
	    },
	    submitHandler:function(form){
            if($("input[name='image_url']").val()==''){
            	alert('请选择图片');
            }else{
            	form.submit();
            }
        }    
	});
});
</script>
</html>