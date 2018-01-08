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
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">文章审核管理</a>><a href="javascript:void(0)">审核发布记录</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>审核发布记录</h3>
			</div>
			<div class="public-content-cont">
			  <#list list as draftAPRecord>
	    	    <label for="">操作时间：${draftAPRecord.create_time?datetime} &nbsp;&nbsp 
	    	                  操作：${draftAPRecord.actionDesc}&nbsp;&nbsp
	    	                  <#if draftAPRecord.operate_desc??>
	    	                  拒绝原因：${draftAPRecord.operate_desc}&nbsp;&nbsp
	    	                  </#if>
	    	    </label><br><br>
	          </#list>
			</div>
		</div>
	</div>
</body>
</html>