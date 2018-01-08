<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js?v=${staticVersion}"></script>
<script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js?v=${staticVersion}"></script>
<script src="/admin/js/support-fileupload.js?v=${staticVersion}"></script>
<script src="/admin/js/ajaxfileupload.js?v=${staticVersion}"></script>
<script src="/admin/js/validation/jquery.validate.js?v=${staticVersion}"></script>
<link href="/admin/plugins/cropper/css/cropper.min.css?v=${staticVersion}" rel="stylesheet">
<link href="/admin/plugins/cropper/css/bootstrap.min.css?v=${staticVersion}" rel="stylesheet">
<script src="/admin/plugins/cropper/js/bootstrap.min.js?v=${staticVersion}"></script>
<script src="/admin/plugins/cropper/js/cropper.min.js?v=${staticVersion}"></script>
<script src="/admin/plugins/cropper/js/main.js?v=${staticVersion}"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">广告内容管理</a>><a href="javascript:void(0)">添加广告</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加广告</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/ad/saveAdvertisement" method="post" id="advertisementForm">
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
				    <img src="<#if advertisement.ad_pic_url??>${imageContextPath}${advertisement.ad_pic_url}</#if>" id="showImage" style="<#if advertisement.ad_pic_url??>display:block;<#else>display:none;</#if>">
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
	<div class="container" id="crop-avatar">
	<h5>修改广告图片</h5>
    <div class="avatar-view" title="Change the avatar">
      <img id="avatar" src="/admin/images/selimg.png" alt="点击选择图片文件">
    </div>
    <div class="file"><button class="form-input-file" onClick="return uploadBase64();">上传裁剪图片</button></div>
    <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <form class="avatar-form" action="${contextPath}/admin/static/ajaxUpload?" enctype="multipart/form-data" method="post">
            <div class="modal-header">
              <button class="close" data-dismiss="modal" type="button">&times;</button>
              <h4 class="modal-title" id="avatar-modal-label">裁剪图片</h4>
            </div>
            <div class="modal-body">
              <div class="avatar-body">
                <div class="avatar-upload">
                  <input class="avatar-src" name="avatar_src" type="hidden">
                  <input class="avatar-data" name="avatar_data" type="hidden">
                  <input class="avatar-input" id="avatarInput" name="avatar_file" type="file">
                </div>
                <div class="row">
                  <div class="col-md-9">
                    <div class="avatar-wrapper"></div>
                  </div>
                </div>
                <div class="row avatar-btns">
                  <div class="col-md-3">
                    <button class="btn btn-primary btn-block avatar-save" type="submit">确定裁剪</button>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
  </div>
<script type="text/javascript">
	$("#status").click(function(){
		if($('#status').is(':checked')) {
			   $("input[name='status']").val("UP");
		}else{
			$("input[name='status']").val("DOWN");
		}
	});
	function uploadBase64(){
		var base64 = $('#avatar')[0].src;
		if(!base64 || base64.indexOf("base64") <= 0){
			alert('请选择图片');
			return;
		}
		var contextPath="${contextPath}";
	    var imageContextPath="${imageContextPath}";
	    var uploadFileName="advertisement";
		var data = {};
		data['base64'] = base64;
		$.ajax(contextPath+'/admin/static/ajaxUploadBase64?imagePath='+uploadFileName+"&positions=BOTTOM_RIGHT&dapt=true", {
        data: data,
        type: 'post',
        dataType: 'json',
        processData: true,
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        success: function (result) {
        	if(typeof(result.error) != 'undefined'){
				if(result.error != ''){
					alert(result.errorMessage);//如有错误则弹出错误
				}else{
					var results=result.list;
					for(var i=0;i<results.length;i++){
						debugger;
						var accessPath=imageContextPath+results[i].watermarkImageUrl;
						$("#showImage").attr("src",accessPath);
						$("input[name='ad_pic_url']").val(results[i].watermarkImageUrl);
						$("#showImage").show();
					}
				}
			}
        },
        complete:function(){$("#avatar").attr('src',contextPath+'/admin/images/selimg.png');},
        error: function (result, status, e){alert(e);}
      });
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