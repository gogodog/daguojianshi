<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script language="javascript" src="${contextPath}/admin/js/My97DatePicker/wdatePicker.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">轮播图管理</a>><a href="javascript:void(0)">添加文章</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加文章</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/saveArticlescrap" method="post" enctype="multipart/form-data">
			    <input type="hidden" name="id" value="${(articlescrap.id)!''}">
			    <input type="hidden" name="status" value="${(articlescrap.status)!'DOWN'}">
				<div class="form-group">
					<label for="">文章标题</label>
					<input class="form-input-txt" type="text" name="title" value="${(articlescrap.title)!''}" maxlengt="100"/>
				</div>
				<div class="form-group">
					<label for="">文章展示时间</label>
					<input  type="text" name="showTime" value="<#if articlescrap.show_time??>${articlescrap.show_time?date}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate"/>
				</div>
				<div class="form-group">
					<label for="">分类</label>
					<select name="type" class="form-select">
					   <#list types as type>
					     <option value="${type}">${type.value}</option>
					   </#list>
				    </select>
				</div>
				<div class="form-group">
					<label for="">作者</label>
					<input class="form-input-txt" type="text" name="author" value="${(articlescrap.author)!''}" maxlength="255" />
				</div>
				<div class="form-group">
				    <label for="">是否上架：</label>
				    <input style="margin-top:9px" type="checkbox" id="status" value="DOWN" <#if articlescrap.status.key?? && articlescrap.status.key==1>checked</#if> />
			    </div>
			    <!--
			    <div class="form-group">
				    <label for="">文章内容</label>
				    <input class="form-input-txt" type="text" name="content" value="${(articlescrap.content)!''}" />
			    </div>
			    -->
			    <div class="form-group">
				    <label for="">文章内容</label>
				    <textarea id="editor_id" name="content"  class="form-input-textara" style="width:700px;height:300px;">
				          ${(articlescrap.content)!''}
				    </textarea> 
			    </div>
				<div class="form-group" style="margin-left:150px;">
					<input type="submit" class="sub-btn" value="提  交" />
					<input type="reset" class="sub-btn" value="重  置" />
				</div>
				</form>
			</div>
		</div>
	</div>
	
	<script src="${contextPath}/admin/js/kingediter/kindeditor-all-min.js"></script>
	<script>
		 KindEditor.ready(function(K) {
	                window.editor = K.create('#editor_id');
	        });
	</script>
	<script type="text/javascript">
	$("#status").click(function(){
		if($('#status').is(':checked')) {
			   $("input[name='status']").val("UP");
		}else{
			$("input[name='status']").val("DOWN");
		}
	});
	</script>
</body>
</html>