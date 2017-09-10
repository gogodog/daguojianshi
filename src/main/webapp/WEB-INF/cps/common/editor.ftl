<div style="text-align:left;margin:1px">
<textarea id="editor_id" name="content"  class="form-input-textara" style="width:100%;height:300px;">
  ${(draft.content)!''}
</textarea> 
</div><!--demo end-->
<button class="btn btn-danger" onclick="saveEditor(1)"><i class="fa fa-toggle-on"></i>保存编辑</button>
<button class="btn btn-danger"><i class="fa fa-bug "></i></i>保存退出</button>
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script src="${contextPath}/admin/js/support-fileupload.js"></script>
<script src="${contextPath}/admin/js/ajaxfileupload.js"></script>
<script>
var contextPath="${contextPath}";
var editorImagePath=contextPath+"/admin/static/ajaxUploadEditorImage?imagePath=editor";
function saveEditor(isBack){
	var title = $("input[name='title']").val();
	var keywordsValue = $("input[name='keywordsValue']").val();
	var author=$("input[name='author']").val();
	var type=$("input[name='type']:checked").val();
	var sub_content=$("#sub_content").val();
	var id=$("input[name='aid']").val();
	editor.sync();
    var content=$("#editor_id").val();
	if(check(title,sub_content,content,author,keywordsValue,type) == 0){
		var jso = {};
		jso["title"]=$.trim(title);
		jso["sub_content"]=$.trim(sub_content);
		jso["content"]=$.trim(content);
		jso["author"]=$.trim(author);
		jso["keywordsValue"]=$.trim(keywordsValue);
		jso["type"]=$.trim(type);
		if($.trim(id).length>0){
			jso["id"]=$.trim(id);
		}
		$.post(contextPath+"/cps/savedraft",jso,function(result){
			if(result.error){
				alert(errorMessage);
			}else{
				if(isBack == '1'){
					location.href=contextPath+"/cps/draft";
				}
			}
	  	});
  	}
}
function check(t,s,c,a,k,y){
	 //length
	if(t.length <2 || t.length >50){
		alert("标题长度不得小于2个字符，大于50个字符");
		return 1;
	}
	if(s.length <20 || s.length >700){
		alert("摘要长度不得小于20个字符，大于700个字符");
		return 1;
	}
	if(c.length <150 || c.length >10000){
		alert("内容文本长度不得小于150个字符，大于10000个字符");
		return 1;
	}
	if(a.length <2 || a.length >20){
		alert("作者长度不得小于2个字符，大于20个字符");
		return 1;
	}
	if(k==null||k==''){
		alert("关键词不能为空");
		return 1;
	}
	if(y==null||y==''){
		alert("类型不能为空");
		return 1;
	}
	return 0;
}
</script>
<script src="${contextPath}/admin/js/kingediter/kindeditor-all.js"></script>
<script>
KindEditor.ready(function(K) {
    window.editor = K.create('#editor_id', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		allowImageRemote : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'image', 'link']
	});
});
</script>