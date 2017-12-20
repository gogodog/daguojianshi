<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js"></script>
<script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js"></script>
<script src="/admin/js/support-fileupload.js"></script>
<script src="/admin/js/ajaxfileupload.js"></script>
<script src="/admin/js/validation/jquery.validate.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">内容管理</a>><a href="javascript:void(0)">添加文章</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改文章信息</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/atcp/updateInfo" method="post" enctype="multipart/form-data" id="articlescrapForm">
			    <input type="hidden" name="id" value="${(articlescrap.id)!''}">
			    <input type="hidden" name="status" value="${(articlescrap.status)}">
			    <div style="display:none" id="pictures">
			    <#if articlescrap.pictures??>
			      <#list  articlescrap.pictures as url>
			          <input type="hidden" name="pictures" value="${(url)!''}">
		          </#list>
		        </#if>
		        </div>
			    <fieldset>    
			    <div class="form-group">
					<label for="">文章标题</label>
					<input class="form-input-txt" type="text" name="title" value="${(articlescrap.title)!''}" maxlengt="100"/>
				</div>
				<div class="form-group">
				    <label for="">文章精简内容</label>
				    <textarea style="margin: 0px; width: 690px; height: 125px;" name="sub_content" maxlength="500">${(articlescrap.sub_content)!''}</textarea>
			    </div>
			    <div class="form-group">
				    <label for="">展示图片</label>
				    <input style="margin-top:9px" type="radio" value="1" name="picm" checked>本地上传
				    <input style="margin-top:9px" type="radio" value="2" name="picm">已有图片链接
				    <div id="showImage">
				      <#if articlescrap.pictures??>
				        <#list  articlescrap.pictures as url>
				          <img src="${url}" style="width:200px;height:200px;"> 
				        </#list>
				      </#if>
				    </div>
				    <div class="file"><input type="file" class="form-input-file" id="uploadImage" name="uploadImage" multiple/>选择文件</div>
				    <div class="file"><input type="button" class="form-input-file" id="buttonUpload" onClick="return ajaxFileUpload();">上传</div>
			    </div>
			    <div class="form-group" style="display:none">
			        <label for=""></label>
			        <textarea style="margin: 0px; width: 690px; height: 125px;" maxlength="500" id="piclinkVal"></textarea>
		        </div>
				<div class="form-group">
					<label for="">文章展示时间</label>
					<input type="text" name="showTime" value="<#if articlescrap.show_time??>${articlescrap.show_time?datetime}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:150px"/>
				</div>
				<div class="form-group">
					<label for="">分类</label>
					<#list types as type>
					  <input style="margin-top:9px" type="radio" name="typeValue" value="${type}" 
			                 <#if articlescrap.type == type>checked</#if>
					  >${type.value}
					</#list>
				</div>
				<div class="form-group">
				    <label for="">关键词</label>
				    <input type="text" class="form-input-txt" name="keywordsValue" value="${(articlescrap.keywordsValue)!''}"/>
				</div>
				<div class="form-group">
				    <label for="">访问量</label>
				    <input class="form-input-txt" type="text" name="visits" value="${(articlescrap.visits)!''}" onkeyup="this.value=this.value.replace(/\D/g,'')" />
			    </div>
			    <div class="form-group">
			        <label for="">文章内容起始时间</label>
			        <select name="start_time_c" class="form-select" style="width: 5%;">
					     <option value="公元" <#if articlescrap.start_time_c == "公元">selected</#if>>公元</option>
					     <option value="公元前" <#if articlescrap.start_time_c == "公元前">selected</#if>>公元前</option>
				    </select>
				    <input class="form-input-txt" style="width:5%;" type="text" name="start_time_y" value="${(articlescrap.start_time_y)!''}" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')"/>年
				    <input class="form-input-txt" style="width:5%;" type="text" name="start_time_m" value="${(articlescrap.start_time_m)!''}" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>月
				    <input class="form-input-txt" style="width:5%;" type="text" name="start_time_d" value="${(articlescrap.start_time_d)!''}" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>日
		        </div>
				<div class="form-group">
					<label for="">作者</label>
					<input class="form-input-txt" type="text" name="author" value="${(articlescrap.author)!''}" maxlength="255" />
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
	
	<script type="text/javascript">
	var contextPath="${contextPath}";
	var editorImagePath=contextPath+"/admin/static/ajaxUploadEditorImage?imagePath=editor";
	
	function ajaxFileUpload()
	{
	    var imageContextPath="${imageContextPath}";
	    var uploadFileName="articlescrap";
	    $.ajaxFileUpload
	    (
	        {
	        	async:false,
	            url:contextPath+'/admin/static/ajaxUpload?imagePath='+uploadFileName+"&adapt=true",
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
		  $("#articlescrapForm").validate({
		    rules: {
			  title: {
		        required: true,
		        rangelength:[1,100]
		      },
		      sub_content: {
		        required: true,
		        rangelength:[1,500]
		      },
		      showTime: {
			    required: true
			  },
			  author:{
				required: true,
				rangelength:[1,20]
			  }
		    },
		    messages: {
		      title: {
		        required: "请输入文章标题",
		        rangelength:"请输入字符在1-100之间"
		      },
		      sub_content: {
		    	required: "请输入文章精简内容",
		    	rangelength:"请输入字符在1-500之间"
		      },
		      showTime: {
			    required: "请输入文章展示时间"
			  },
			  author:{
			    required: "请输入作者",
			    rangelength:"作者名字长度在1-20之间"
			  }
		    },
		    submitHandler:function(form){
	           var start_time_m=$("input[name='start_time_m']").val();
	           var start_time_d=$("input[name='start_time_d']").val();
	           var picm=$("input[name='picm']:checked").val();
	           if(picm==2){
	        	  var pictures='';
	        	  var piclinkVal=$("#piclinkVal").val();
	        	  var picUrl=piclinkVal.split("\n");
	        	  for(var i=0;i<picUrl.length;i++){
	        		  pictures+="<input type=\"hidden\" name=\"pictures\" value=\""+picUrl[i]+"\">";
	        	  }
	        	  $("#pictures").html(pictures);
	           }
	           if(start_time_m!=''&&start_time_m!=null&&parseInt(start_time_m)>12){
	        	   alert("请您选择正确的月份")
	           }else if(start_time_d!=''&&start_time_d!=null&&parseInt(start_time_d)>31){
	        	   alert("请您选择正确的日期")
	           }else{
	               form.submit();
	           }
	        }    
		});
	});
	
    $("input[name='picm']").change(function(){
    	var checkeVal=$(this).val();
    	if(checkeVal=='2'){
    		$("#piclinkVal").parent().show();
    		$("#uploadImage").parent().hide();
    		$("#buttonUpload").parent().hide();
    	}else if(checkeVal=='1'){
    		$("#piclinkVal").parent().hide();
    		$("#uploadImage").parent().show();
    		$("#buttonUpload").parent().show();
    	}
    })
	</script>
</body>
</html>