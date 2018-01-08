<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js?v=${staticVersion}"></script>
<script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js?v=${staticVersion}"></script>
<script src="/admin/js/support-fileupload.js?v=${staticVersion}"></script>
<script src="/admin/js/ajaxfileupload.js?v=${staticVersion}"></script>
<script src="/admin/js/validation/jquery.validate.js?v=${staticVersion}"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">文章管理</a>><a href="javascript:void(0)">添加文章</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加文章</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/atcp/updateContent" method="post" enctype="multipart/form-data" id="articlescrapForm">
			    <input type="hidden" name="articlescrapId" value="${(articlescrapId)!''}">
			    <div class="form-group">
				    <label for="">文章内容</label>
				    <textarea id="editor_id" name="content"  class="form-input-textara" style="width:700px;height:300px;">
				          ${(content)!''}
				    </textarea> 
			    </div>
				<div class="form-group" style="margin-left:150px;">
					<input type="submit" class="sub-btn" value="提  交" />
				</div>
				</fieldset>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	var contextPath="${contextPath}";
	var editorImagePath=contextPath+"/admin/static/ajaxUploadEditorImage?imagePath=editor";
	$().ready(function() {
		  // 在键盘按下并释放及提交后验证提交表单
		  $("#articlescrapForm").validate({
		    rules: {
			  content:{
			    required: true
			  }
		    },
		    messages: {
			  content:{
				required: "请输入文章内容"
			  }
		    },
		    submitHandler:function(form){
		       KindEditor.sync();
	           var content=$("#editor_id").val();
	           if(content ==''){
	        	   alert("请输入文章内容")
	           }else{
	        	   alert(content);
	           }
	        }    
		});
	});
	</script>
	<script src="/admin/js/kingediter/kindeditor-all.js?v=${staticVersion}"></script>
	<script>
		 KindEditor.ready(function(K) {
	                window.editor = K.create('#editor_id', {
		resizeType : 1,
		pasteType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		allowImageRemote : true,
		dataUrl:contextPath+"/cps/userPics/ajaxList",
		scListPage:contextPath+"/cps/userPics/list",
		scListPageCallBack:function(){
			alert("callback...");
		},
		cssData: 'body {font-family: "微软雅黑"; font-size: 16px}',
		items : [
			'source',
			'undo', 'redo', '|', 
			'preview','plainpaste', '|', 
			'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent','superscript', 'clearhtml', 'quickformat', 'selectall', '|',
			//'fontname', 'fontsize', '|', 
			'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'lineheight', 'removeformat', '|', 
			'image','hr','|', 
			'fullscreen','about'
		]
	});
	        });
	</script>
</body>
</html>