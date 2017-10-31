<div style="text-align:left;margin:1px">
<textarea id="editor_id" name="content"  class="form-input-textara" style="width:100%;height:300px;">
  ${(draft.content)!''}
</textarea> 
</div>
<button class="btn btn-danger" onclick="saveEditor(1)"><i class="fa fa-toggle-on"></i>保存编辑</button>
<button class="btn btn-danger" onclick="preview();"><i class="fa fa-bug "></i></i>预览</button>
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
	var start_time_c=$("select[name='start_time_c']").find("option:selected").val();
	var start_time_y=$("input[name='start_time_y']").val();
	var start_time_m=$("input[name='start_time_m']").val();
	var start_time_d=$("input[name='start_time_d']").val();
	editor.sync();
    var content=$("#editor_id").val();
    var jso = {};
	jso["title"]=$.trim(title);
	jso["sub_content"]=$.trim(sub_content);
	jso["content"]=$.trim(content);
	jso["author"]=$.trim(author);
	jso["keywordsValue"]=$.trim(keywordsValue);
	jso["type"]=$.trim(type);
	jso["start_time_c"]=$.trim(start_time_c);
	jso["start_time_y"]=$.trim(start_time_y);
	jso["start_time_m"]=$.trim(start_time_m);
	jso["start_time_d"]=$.trim(start_time_d);
	if(check(jso) == 0){
		if($.trim(id).length>0){
			jso["id"]=$.trim(id);
		}
		$.post(contextPath+"/cps/dft/savedraft",jso,function(result){
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
function check(jso){
	 //length
	if(jso.title.length <2 || jso.title.length >50){
		alert("标题长度不得小于2个字符，大于50个字符");
		return 1;
	}
	if(jso.sub_content.length <20 || jso.sub_content.length >700){
		alert("摘要长度不得小于20个字符，大于700个字符");
		return 1;
	}
	if(jso.content.length <150 || jso.content.length >10000){
		alert("内容文本长度不得小于150个字符，大于10000个字符");
		return 1;
	}
	if(jso.author.length <2 || jso.author.length >20){
		alert("作者长度不得小于2个字符，大于20个字符");
		return 1;
	}
	if(jso.keywordsValue==null||jso.keywordsValue==''){
		alert("关键词不能为空");
		return 1;
	}
	if(jso.type==null||jso.type==''){
		alert("类型不能为空");
		return 1;
	}
	return 0;
}

function preview(){
	var aid=$("input[name='aid']").val();
	window.location.href = contextPath+"/cps/dft/previewDraft?aid="+aid;
}
</script>
<script src="${contextPath}/admin/js/kingediter/kindeditor-all2.js"></script>
<script>
KindEditor.ready(function(K) {
    window.editor = K.create('#editor_id', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		allowImageRemote : true,
		dataUrl:"/sapi/cs",
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'image', 'link']
	});
});
</script>