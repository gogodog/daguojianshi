<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script language="javascript" src="${contextPath}/admin/js/My97DatePicker/wdatePicker.js"></script>
<script src="${contextPath}/admin/js/support-fileupload.js"></script>
<script src="${contextPath}/admin/js/ajaxfileupload.js"></script>
<script src="${contextPath}/admin/js/validation/jquery.validate.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">广告内容管理</a>><a href="javascript:void(0)">添加广告</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加广告</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/saveAdvertisement" method="post" id="advertisementForm">
			    <input type="hidden" name="id" value="${(advertisement.id)!''}">
			    <input type="hidden" name="status" value="${(advertisement.status)!'DOWN'}">
			    <input type="hidden" name="ad_pic_url" value="${(advertisement.ad_pic_url)!''}">
			    <fieldset>    
			    <div class="form-group">
					<label for="">广告描述</label>
					<input class="form-input-txt" type="text" name="ad_desc" value="${(advertisement.ad_desc)!''}" maxlengt="100"/>
				</div>
				<div class="form-group">
				    <label for="">广告链接</label>
				    <input class="form-input-txt" type="text" name="ad_link_url" value="${(advertisement.ad_link_url)!''}" maxlengt="100"/>
			    </div>
			    <div class="form-group">
			        <label for="">广告位置：</label>
			        <select name="ad_position" id="ad_position">
			            <option value="">请选择</option>
			            <#list adPositions as adPosition>
			               <option value="${adPosition}"<#if advertisement.ad_position?? && advertisement.ad_position == adPosition>selected</#if> >${adPosition.value}</option>
			            </#list>
			        </select>
		        </div>
		        <div class="form-group">
		            <label for="">开始展示时间</label>
		            <input type="text" name="start_show_time" value="<#if advertisement.start_show_time??>${advertisement.start_show_time?datetime}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:150px"/>
	            </div>
	            <div class="form-group">
	                <label for="">结束展示时间</label>
	                <input type="text" name="end_show_time" value="<#if advertisement.end_show_time??>${advertisement.end_show_time?datetime}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:150px"/>
                </div>
                <div class="form-group">
			        <label for="">是否上架：</label>
			        <input style="margin-top:9px" type="checkbox" id="status" value="DOWN" <#if advertisement.status.key?? && advertisement.status.key==1>checked</#if> />
		        </div>
			    <div class="form-group">
				    <label for="">广告图片</label>
				    <img src="<#if advertisement.ad_pic_url??>${imageContextPath}${advertisement.ad_pic_url}</#if>" id="showImage" style="width:200px;height:200px;<#if advertisement.ad_pic_url??>display:block;<#else>display:none;</#if>">
				    <div class="file"><input type="file" class="form-input-file" id="uploadImage" name="uploadImage"/>选择文件</div>
				    <div class="file"><input type="button" class="form-input-file" id="buttonUpload" onClick="return ajaxFileUpload();">上传</div>
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
	$("#status").click(function(){
		if($('#status').is(':checked')) {
			   $("input[name='status']").val("UP");
		}else{
			$("input[name='status']").val("DOWN");
		}
	});
	function ajaxFileUpload()
	{
	    var contextPath="${contextPath}";
	    var imageContextPath="${imageContextPath}";
	    var uploadFileName="advertisement";
	    $.ajaxFileUpload
	    (
	        {
	        	async:false,
	            url:contextPath+'/admin/ajaxUpload?imagePath='+uploadFileName,//这个是要提交到上传的文件
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
	                    	var accessPath=imageContextPath+result.imageUrl;
	                        $("#showImage").attr("src",accessPath);
	                        $("input[name='ad_pic_url']").val(result.imageUrl);
	                        $("#showImage").show();
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
		  $("#advertisementForm").validate({
		    rules: {
			  ad_desc: {
		        required: true,
		        rangelength:[1,100]
		      },
		      ad_link_url: {
		        required: true
		      },
		      start_show_time: {
			    required: true
			  },
			  end_show_time:{
				required: true
			  }
		    },
		    messages: {
		      ad_desc: {
		        required: "请输入广告描述",
		        rangelength:"请输入字符在1-100之间"
		      },
		      ad_link_url: {
		    	required: "请输入广告链接"
		      },
		      start_show_time: {
			    required: "请输入开始展示时间"
			  },
			  end_show_time:{
				required: "请输入结束展示时间"
			  }
		    },
		    submitHandler:function(form){
	           var ad_pic_url=$("input[name='ad_pic_url']").val();
	           var ad_position=$("#ad_position").val();
	           if(ad_position==''){
	        	   alert("请选择广告位置");
	           }else if(ad_pic_url==''&&(ad_position=='INDEX_FIRST'||ad_position=='INDEX_SECOND')){
	        	   alert("请选择广告图片");
	           }else{
	               form.submit();
	           }
	        }    
		});
	});
	</script>
</body>
</html>