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
		<div class="public-nav">您当前的位置：<a href="">后台用户管理</a>><a href="">操作日志</a>><a href="javascript:void(0)">日志详情</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>日志详情</h3>
			</div>
			<div class="public-content-cont">
		    	<label for="">入参：</label>${operateLog.param}<br><br>
		        <label for="">错误信息：</label>${operateLog.errorMessage}
			</div>
		</div>
	</div>
	
</body>
</html>