<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="/admin/css/main.css">
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js?v=${staticVersion}"></script>
<script src="/admin/js/support-fileupload.js?v=${staticVersion}"></script>
<script src="/admin/js/ajaxfileupload.js?v=${staticVersion}"></script>
<script src="/admin/js/validation/jquery.validate.js?v=${staticVersion}"></script>
<script src="/admin/js/jquery.autocompleter.min.js?v=${staticVersion}"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">后台用户管理</a>><a href="">组织管理</a>><a href="javascript:void(0)">修改组织</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改组织</h3>
			</div>
			<div class="public-content-cont">
			    <fieldset>    
			    <input type="hidden" name="id" value="${(dto.organization.id)!''}">
			    <input type="hidden" name="proxy" value="${(dto.organization.proxy)!''}">
				<div class="form-group">
					<label for="">组织名称</label>
					<input class="form-input-txt" type="text" name="oname" value="${(dto.organization.oname)!''}" maxlength="50"/>
				</div>
				<div class="form-group">
				    <label for="">组织简介</label>
				    <input class="form-input-txt" type="text" name="summary" value="${(dto.organization.summary)!''}" maxlength="127"/>
			    </div>
			    <div class="form-group">
			      <label for="">代理人code</label>
		          <input class="form-input-txt" type="text" name="nope" id="proxy" maxlength="70" value = "${dto.userCode}" />
		        </div>
				<div class="form-group" style="margin-left:150px;">
					<input type="button" class="sub-btn" value="提  交" onclick="validateAndSubmit();"/>
					<input type="reset" class="sub-btn" value="重  置" />
				</div>
				</fieldset>
			</div>
		</div>
	</div>
</body>
<script>
var data = [
            { "value": "1", "label": "one" },
            { "value": "2", "label": "two" },
            { "value": "3", "label": "three" }
        ];
//$('#proxy').autocompleter({ 
////	source: 'path/to/get_data_request' 
//	source: data 
//});

//$('#proxy').autocompleter({
//    // marker for autocomplete matches
//    highlightMatches: true,
//
//    // object to local or url to remote search
//    source: data,
//
//    // custom template
//    template: '{{ label }} <span>({{ hex }})</span>',
//
//    // show hint
//    hint: true,
//
//    // abort source if empty field
//    empty: false,
//
//    // max results
//    limit: 5,
//
//    callback: function (value, index, selected) {
//        if (selected) {
//            $('.icon').css('background-color', selected.hex);
//        }
//    }
//});


function validateAndSubmit(){
	var contextPath = '${contextPath}';
	var oname = $("input[name='oname']").val();
	if(oname == null || oname.length == 0){
		alert('组织名称不能为空');
		return;
	}
	var summary = $.trim($("input[name='summary']").val());
	var proxyUserCode = $.trim($("#proxy").val());
	var id = $.trim($("input[name='id']").val());
	var url = "";
	if(id!=null && id.length>0){
		url = contextPath+"/admin/ognztn/update";
	}else{
		url = contextPath+"/admin/ognztn/save";
	}
	$.ajax({
		async:false,
		data:{id:id,oname:oname,summary:summary,proxyUserCode:proxyUserCode},
		dataType: "json",
		url:url,
		type:"POST",
		success:function(data) {
            if(data.error){
            	if(data.errorCode!='SUCCESS'){
            		alert(data.errorMessage);
            	}
            }else{
            	window.location.href=contextPath+"/admin/ognztn/list";
            }
        }, 
        error:function(){
            console.log("服务器繁忙...");
        }
	});
}
</script>
</html>