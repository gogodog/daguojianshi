<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">轮播图管理</a>><a href="${contextPath}/admin/carousel">添加轮播图</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加轮播</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/saveCarousel" method="post" enctype="multipart/form-data">
			    <input type="hidden" name="id" value="${(carousel.id)!''}">
			    <input type="hidden" name="status" value="${(carousel.status)!'DOWN'}">
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
					<!--<input class="form-input-txt" type="text" name="image_url" value="UploadFiles/20167111477414.jpg" />-->
					<#if carousel.image_url??>
					    <img src="${carousel.image_url}" style="width:200px;height:200px">
					</#if>
					<div class="file"><input type="file" class="form-input-file" id="uploadImage" name="uploadImage"/>选择文件</div>
				</div>
				<div class="form-group">
				    <label for="">是否上架：</label>
				    <input style="margin-top:9px" type="checkbox" id="status" value="" <#if carousel.status.key?? && carousel.status.key==1>checked</#if> />
			    </div>
				<div class="form-group" style="margin-left:150px;">
					<input type="submit" class="sub-btn" value="提  交" />
					<input type="reset" class="sub-btn" value="重  置" />
				</div>
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
</script>
</html>