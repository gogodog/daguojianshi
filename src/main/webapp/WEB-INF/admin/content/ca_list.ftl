<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="/admin/css/xcConfirm.css"/>
<script src="/admin/js/jquery-1.11.1.min.js"></script>
<script src="/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">频道管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">关联文章列表</h3>
				<div class="public-content-right fr">
				<a href="javascript:void(0)" onclick="showCAPop();"
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加文章</a>
			</div>
			</div>
			<input type="hidden" name="channel_id" value="${channelId}">
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:10%">频道</th>
						<th style="width:20%">文章id</th>
						<th style="width:30%">文章标题</th>
						<th style="width:10%">排序</th>
						<th style="width:30%">操作</th>
					</tr>
					<#list caList as ca>
					  <tr>
					     <td>${channelName}</td>
					     <td>${ca.channelArticlescrap.articlescrap_id}</td>						
					     <td>${ca.title}</td>
					     <td>${ca.channelArticlescrap.sort}</td>
					     <td>
					     	<div class="table-fun-1">
					     		<a href="javascript:void(0)" onclick="deleteCAX('${ca.channelArticlescrap.id}');">删除</a>
					     		<a href="javascript:void(0)" onclick="update('${ca.channelArticlescrap.id}',this);">修改</a>
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
function deleteCAX(caId){
	var txt= "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		$.ajax({
		   async:false,
		   data:{caId:caId},
		   dataType: "json",
		   url:contextPath+"/admin/channel/deleteCA",
		   type:"POST",
		   success:function(data) {
               if(data.error){
            	    alert('服务器繁忙...');
               }else{
               	    window.location.reload();
               }
           }, 
           error:function(){
               console.log("服务器繁忙...");
           }
	});
	}})
}

function showCAPop(){
	var txt = "文章id：<input type=\"text\" name=\"a_id\" class=\"inputBox\" style=\"width:120px;\"><br>"
			+ "排序：<input type=\"text\" name=\"p_sort\" class=\"inputBox\" style=\"width:120px;\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"><br>";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		saveOrUpdate();
	}})
}

function saveOrUpdate(){
	var a_id=$("input[name='a_id']").val();
	var p_sort=$("input[name='p_sort']").val();
	var channelId=$("input[name='channel_id']").val();
	if(a_id==''||a_id==null){
		alert('请输入文章id');
		return;
	}
	if(p_sort==null||p_sort==''){
		alert('请输入排序');
		return;
	}
	$.ajax({
		async:false,
		data:{channel_id:channelId,articlescrap_id:a_id,sort:p_sort},
		dataType: "json",
		url:contextPath+"/admin/channel/saveCA",
		type:"POST",
		success:function(data) {
            if(data.error){
            	if(data.errorCode=='SERVICE_ERROR'){
            		alert(data.errorMessage);
            	}else if(data.errorCode!='SUCCESS'){
            		alert('服务器繁忙...');
            	}
            }else{
            	window.location.reload();
            }
        }, 
        error:function(){
            console.log("服务器繁忙...");
        }
	});
}

function update(caId,obj){
	var x=$(obj).parent().parent().prev();
	var sort=x.html();
	x.html("<input type=\"text\" value=\""+sort+"\" style=\"width:30px\" onBlur=\"updateSort(this,'"+caId+"');\">");
}

function updateSort(obj,caId){
	var sort = $(obj).val();
	$.ajax({
		async:false,
		data:{caId:caId,sort:sort},
		dataType: "json",
		url:contextPath+"/admin/channel/updateCA",
		type:"POST",
		success:function(data) {
            if(data.error){
            	if(data.errorCode!='SUCCESS'){
            		alert('服务器繁忙...');
            	}
            }else{
            	$(obj).parent().html(sort);
            }
        }, 
        error:function(){
            console.log("服务器繁忙...");
        }
	});
}
</script>
</html>