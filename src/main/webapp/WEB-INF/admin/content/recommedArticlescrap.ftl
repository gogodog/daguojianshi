<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">文章推荐管理</a>><a href="">添加推荐</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加推荐</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/saveRecommedArticlescrap" method="post">
			    <input type="hidden" name="status">
				<div class="form-group">
					<label for="">排序</label>
					<input class="form-input-txt" type="text" name="sort" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
				</div>
				<div class="form-group">
					<label for="">文章id</label>
					<input class="form-input-txt" type="text" name="articlescrap_id" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
				</div>
				<div class="form-group">
				    <label for="">是否上架：</label>
				    <input style="margin-top:9px" type="checkbox" id="status" />
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