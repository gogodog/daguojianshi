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
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">首页配置管理</a>><a href="">添加首页配置</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加首页配置</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/idxcfg/saveOrUpdate" method="post" enctype="multipart/form-data" id="indexConfigForm">
			    <fieldset>    
			    <input type="hidden" name="id" value="${(config.indexConfig.id)!''}">
			    <input type="hidden" name="status" value="${(config.indexConfig.status)!'DOWN'}">
			    <div style="display:none" id="pictures">
			    <#if config.pics??>
			      <#list  config.pics as url>
			          <input type="hidden" name="pics" value="${(url)!''}">
		          </#list>
		        </#if>
		        </div>
				<div class="form-group">
					<label for="">文章id</label>
					<input class="form-input-txt" type="text" name="aid" value="${(config.indexConfig.aid)!''}"/>
				</div>
				<div class="form-group">
					<label for="">标题</label>
					<input class="form-input-txt" type="text" name="title" value="${(config.indexConfig.title)!''}" maxlength="255"/>
				</div>
				<div class="form-group">
			        <label for="">精简内容</label>
			        <textarea style="margin: 0px; width: 690px; height: 125px;" name="sub_content" maxlength="500">${(config.indexConfig.sub_content)!''}</textarea>
		        </div>
				<div class="form-group">
				    <label for="">排序</label>
				    <input class="form-input-txt" type="text" name="sort" value="${(config.indexConfig.sort)!''}" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
			    </div>
			    <div class="form-group">
			        <label for="">展示图片</label>
			        <div id="showImage">
			           <#if config.pics??>
			             <#list  config.pics as url>
			               <img src="${imageContextPath}${url}" style="width:200px;height:200px;"> 
			             </#list>
			           </#if>
			        </div>
			        <div class="file"><input type="file" class="form-input-file" id="uploadImage" name="uploadImage" multiple/>选择文件</div>
			        <div class="file"><input type="button" class="form-input-file" id="buttonUpload" onClick="return ajaxFileUpload();">上传</div>
		        </div>
				<div class="form-group">
		            <label for="">类型</label>
		            <select name="type">
		               <#list types as type>
		                  <option <#if config.indexConfig.type?? && config.indexConfig.type == type>selected</#if> value="${type}">${type.value}</option>
		               </#list>
		           </select>
	            </div>
				<div class="form-group">
			        <label for="">位置</label>
			        <select name="position">
			              <#list 1..2 as i>
			                <option value="${i}" <#if config.indexConfig.position?? && config.indexConfig.position == i>selected</#if>>${i}</option>
			              </#list>
			        </select>
		        </div>
				<div class="form-group">
				    <label for="">是否上架：</label>
				    <input style="margin-top:9px" type="checkbox" id="status" value="" <#if config.indexConfig.status?? && config.indexConfig.status.key==1>checked</#if> />
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
var contextPath='${contextPath}';
$("#status").click(function(){
	if($('#status').is(':checked')) {
		   $("input[name='status']").val("UP");
	}else{
		$("input[name='status']").val("DOWN");
	}
});

function ajaxFileUpload()
{
    var imageContextPath="${imageContextPath}";
    var uploadFileName="m_index_config";
    $.ajaxFileUpload
    (
        {
        	async:false,
            url:contextPath+'/admin/static/ajaxUpload?imagePath='+uploadFileName,//这个是要提交到上传的php程序文件
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
                    	var results=result.list;
                    	var divImages="";
                    	var pictures="";
                    	for(var i=0;i<results.length;i++){
                    		var accessPath=imageContextPath+results[i].minImageUrl;
                    		divImages+="<img src=\""+accessPath+"\" style=\"width:200px;height:200px;\">";
                    		pictures+="<input type=\"hidden\" name=\"pictures\" value=\""+results[i].minImageUrl+"\">";
                    	}
                    	$("#showImage").html(divImages);
                    	$("#pictures").html(pictures);
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
	  $("#indexConfigForm").validate({
	    rules: {
	      sort: {
	        required: true,
	        digits:true
	      },
	      aid: {
	        required: true,
	      }
	    },
	    messages: {
	      sort: {
	        required: "请输入排序",
	        digits: "请输入整数"
	      },
	      aid: {
	    	required: "请输入文章id"
	      }
	    },
	    submitHandler:function(form){
            form.submit();
        }    
	});
});
</script>
</html>