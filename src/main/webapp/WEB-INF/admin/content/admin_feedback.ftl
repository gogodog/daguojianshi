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
		<div class="public-nav">您当前的位置：<a href="">反馈管理</a>><a href="">后台反馈管理</a>><a href="javascript:void(0)">反馈详情</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>回复消息</h3>
			</div>
			<input type="hidden" name="relatedId" value="${relatedId}" id="relatedId">
			<div class="public-content-cont">
			   <#list list as noticeMessage>
			      <label for="">${noticeMessage.create_time?datetime}：</label>${noticeMessage.message}<br><br>
               </#list>
               <span>
                  <textarea style="margin: 0px; width: 690px; height: 125px;" name="message" id="message" maxlength="255"></textarea>
               </span>
               <div class="form-group" style="margin-left:150px;">
				 <input type="button" class="sub-btn" value="提  交" onclick="saveMessage();"/>
			   </div>
			</div>
		</div>
	</div>
	<script>
	   var contextPath = '${contextPath}';
       function saveMessage(){
    	  var message = $("#message").val();
    	  var relatedId = $("#relatedId").val();
    	  $.ajax({
  			async:false,
  			data:{feedbackId:relatedId,message:message},
  			dataType: "json",
  			url:contextPath+"/admin/afb/reply",
  			type:"POST",
  			success:function(data) {
  	            if(data.error){
  	            	alert(data.errorMessage);
  	            }else{
  	            	window.location.reload();
  	            }
  	        }, 
  	        error:function(){
  	            console.log("服务器繁忙...");
  	        }
  		});
       }
	</script>
</body>
</html>