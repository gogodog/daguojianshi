<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script src="${contextPath}/admin/js/support-fileupload.js"></script>
<script src="${contextPath}/admin/js/ajaxfileupload.js"></script>
<script src="${contextPath}/admin/js/validation/jquery.validate.js"></script>
<link href="${contextPath}/admin/plugins/cropper/css/cropper.min.css" rel="stylesheet">
<link href="${contextPath}/admin/plugins/cropper/css/bootstrap.min.css" rel="stylesheet">
<script src="${contextPath}/admin/plugins/cropper/js/bootstrap.min.js"></script>
<script src="${contextPath}/admin/plugins/cropper/js/cropper.min.js"></script>
<script src="${contextPath}/admin/plugins/cropper/js/main.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">轮播图管理</a>><a href="${contextPath}/admin/cul/carousel">添加轮播图</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加轮播</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/cul/saveCarousel" method="post" enctype="multipart/form-data" id="carouselForm">
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
					<img src="<#if carousel.image_url??>${imageContextPath}${carousel.image_url}</#if>" id="showImage" name="showImage" style="<#if carousel.image_url??>display:block;<#else>display:none;</#if>">
				</div>
				<div class="form-group">
			        <label for="">位置</label>
			        <select name="position">
			           <#list positions as position>
			              <option <#if carousel.position?? && carousel.position == position>selected</#if> value="${position}">${position.value}</option>
			           </#list>
			        </select>
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
				<div class="container" id="crop-avatar">
	<h5>修改广告图片</h5>
    <div class="avatar-view" title="Change the avatar">
      <img id="avatar" src="${contextPath}/admin/images/selimg.png" alt="点击选择图片文件">
    </div>
    <div class="file"><button class="form-input-file" onClick="return uploadBase64();">上传裁剪图片</button></div>
    <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <form class="avatar-form" action="${contextPath}/admin/static/ajaxUpload?" enctype="multipart/form-data" method="post">
            <div class="modal-header">
              <button class="close" data-dismiss="modal" type="button">&times;</button>
              <h4 class="modal-title" id="avatar-modal-label">裁剪图片</h4>
            </div>
            <div class="modal-body">
              <div class="avatar-body">
                <div class="avatar-upload">
                  <input class="avatar-src" name="avatar_src" type="hidden">
                  <input class="avatar-data" name="avatar_data" type="hidden">
                  <input class="avatar-input" id="avatarInput" name="avatar_file" type="file">
                </div>
                <div class="row">
                  <div class="col-md-9">
                    <div class="avatar-wrapper"></div>
                  </div>
                </div>
                <div class="row avatar-btns">
                  <div class="col-md-3">
                    <button class="btn btn-primary btn-block avatar-save" type="submit">确定裁剪</button>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
  </div>
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
function uploadBase64(){
		var base64 = $('#avatar')[0].src;
		if(!base64 || base64.indexOf("base64") <= 0){
			alert('请选择图片');
			return;
		}
		var contextPath="${contextPath}";
	    var imageContextPath="${imageContextPath}";
	    var uploadFileName="carousel";
		var data = {};
		data['base64'] = base64;
		$.ajax(contextPath+'/admin/static/ajaxUploadBase64?imagePath='+uploadFileName+"&dapt=true", {
        data: data,
        type: 'post',
        dataType: 'json',
        processData: true,
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        success: function (result) {
        	if(typeof(result.error) != 'undefined'){
				if(result.error != ''){
					alert(result.errorMessage);//如有错误则弹出错误
				}else{
					var results=result.list;
					for(var i=0;i<results.length;i++){
						var accessPath=imageContextPath+results[i].minImageUrl;
						$("#showImage").attr("src",accessPath);
						$("input[name='image_url']").val(results[i].minImageUrl);
						$("#showImage").show();
					}
				}
			}
        },
        complete:function(){$("#avatar").attr('src',contextPath+'/admin/images/selimg.png');},
        error: function (result, status, e){alert(e);}
      });
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