<div style="text-align:left;">
    <div id="divDemo"><i>请输入内容...</i></div>
</div><!--demo end-->
<button class="btn btn-danger" onclick="saveEditor(1)"><i class="fa fa-toggle-on"></i>保存编辑</button>
<button class="btn btn-danger"><i class="fa fa-bug "></i></i>保存退出</button>
<script type="text/javascript" src='${contextPath}/cps/plugin/editor/jquery.js'></script>
<script type="text/javascript" src='${contextPath}/cps/plugin/editor/bootstrap.min.js'></script>
<script type="text/javascript" src='${contextPath}/cps/plugin/editor/editor.min.js'></script>
<script type="text/javascript" src="${contextPath}/cps/plugin/editor/index.js"></script>
<script>
function saveEditor(isBack){
	var title = $('#title').val();
	var sub = $('#sub').val();
	var content = getHtml();
	var id = $('#id').val();
	if(check(title,sub,content) == 0){
		var jso = {};
		jso["title"]=title;
		jso["sub"]=sub;
		jso["content"]=content;	
		$.post("/cps/savedraft",jso,function(error){
			if(error.code == 0){
				if(isBack){
					location.href = "/cps/draft";
					return;
				}
			}
			console.log(error.msg);
	  	});
  	}
}
function check(t,s,c){
	 //length
	if(t.length <8 || t.length >200){
		alert("标题长度不得小于8个字符，大于200个字符");
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
	return 0;
}
</script>