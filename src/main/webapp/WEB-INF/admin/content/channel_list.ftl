<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="/admin/css/xcConfirm.css?v=${staticVersion}"/>
<script src="/admin/js/jquery-1.11.1.min.js?v=${staticVersion}"></script>
<script src="/admin/js/confirm/xcConfirm.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">频道管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">频道列表</h3>
				<div class="public-content-right fr">
				<a href="javascript:void(0)" onclick="showChannelPop();"
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加频道</a>
			</div>
			</div>
			
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:10%">频道名称</th>
						<th style="width:10%">状态</th>
						<th style="width:10%">频道排序</th>
						<th style="width:20%">创建时间</th>
						<th style="width:20%">修改时间</th>
						<th style="width:30%">操作</th>
					</tr>
					<#list channels as channel>
					  <tr>
					     <td>${channel.c_name}</td>
					     <td>${channel.status.value}</td>						
					     <td>${channel.sort}</td>
					     <td>${channel.create_time?datetime}</td>
					     <td>${channel.update_time?datetime}</td>
					     <td>
					     	<div class="table-fun-1">
					     	    <a href="/admin/channel/caList?channelId=${channel.id}&channelName=${channel.c_name}">关联文章</a>
					     		<a href="javascript:void(0)" onclick="update('${channel.id}','${channel.c_name}','${channel.status}','${channel.sort}');">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteChannel('${channel.id}');">删除</a>
					     	</div>
					     </td>
				     </tr>
				   </#list>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
var contextPath="${contextPath}";
function deleteChannel(channelId){
	var txt=  "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		window.location.href=contextPath+"/admin/channel/delete?channel_id="+channelId;
	}})
}

function showChannelPop(channelId){
	var txt = "频道名称：<input type=\"text\" name=\"p_name\" class=\"inputBox\" style=\"width:120px;\"><br>"
			+ "排序：<input type=\"text\" name=\"p_sort\" class=\"inputBox\" style=\"width:120px;\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"><br>"
	        + "状态：<input type=\"checkbox\" name=\"p_status\"><br>";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		saveOrUpdate(null);
	}})
}

function update(channelId,c_name,status,sort){
	var isChecked = status == 'UP'?'checked':'';
	var txt = "频道名称：<input type=\"text\" value=\""+c_name+"\" name=\"p_name\" class=\"inputBox\" style=\"width:120px;\"><br>"
			+ "排序：<input type=\"text\" value=\""+sort+"\" name=\"p_sort\" class=\"inputBox\" style=\"width:120px;\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"><br>"
	        + "状态：<input type=\"checkbox\" name=\"p_status\" "+isChecked+"><br>";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		saveOrUpdate(channelId);
	}})
}

function saveOrUpdate(channelId){
	var p_name=$("input[name='p_name']").val();
	var p_sort=$("input[name='p_sort']").val();
	var p_status='';
	if($("input[name='p_status']").is(':checked')) {
		p_status='UP';
	}else{
		p_status='DOWN';
	}
	if(p_sort==null||p_sort==''){
		alert('请输入排序');
		return;
	}
	if(p_name==null||p_name.length<1||p_name.length>10){
		alert('频道名称不能为空且长度不能超过10');
		return;
	}
	$.ajax({
		async:false,
		data:{id:channelId,c_name:p_name,sort:p_sort,status:p_status},
		dataType: "json",
		url:contextPath+"/admin/channel/save",
		type:"POST",
		success:function(data) {
            if(data.error){
            	if(data.errorCode!='SUCCESS'){
            		alert('服务器繁忙...');
            	}
            }else{
            	alert('填写成功');
            	window.location.href=contextPath+"/admin/channel/list";
            }
        }, 
        error:function(){
            console.log("服务器繁忙...");
        }
	});
}
</script>
</html>