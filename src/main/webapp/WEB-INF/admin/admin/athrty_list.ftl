<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="/admin/css/xcConfirm.css?v=${staticVersion}"/>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">后台用户管理</a><a href="javascript:void(0)">权限列表</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">权限列表</h3>
				<div class="public-content-right fr">
				  <a href="javascript:void(0)"
				     style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center" onclick="showAuthorityPop();">添加权限</a>
			    </div>
			</div>
			<div class="public-content-cont">
			    <table class="public-cont-table">
					<tr>
					    <th style="width:5%">权限id</th>
						<th style="width:10%">权限名称</th>
						<th style="width:10%">权限路径</th>
						<th style="width:10%">创建时间</th>
						<th style="width:10%">修改时间</th>
						<th style="width:14%">操作</th>
					</tr>
					<#list list as authority>
					  <tr>
					     <td>${authority.id}</td>			
					     <td>${authority.authority_name}</td>
					     <td>${authority.authority_url}</td>	
					     <td>${authority.create_time?datetime}</td>	
					     <td>${authority.update_time?datetime}</td>	
					     <td>
					     	<div class="table-fun">
					     		<a href="javascript:void(0)" dataId="${authority.id}" onclick="updateAuthority(this);">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteAuthority('${authority.id}');">删除</a>
					     	</div>
					     </td>
				     </tr>
				   </#list>
				</table>
			</div>
		</div>
	</div>
<script src="/admin/js/jquery-1.11.1.min.js?v=${staticVersion}"></script>
<script src="/admin/js/confirm/xcConfirm.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
<script src="/admin/js/page.js?v=${staticVersion}" charset="utf-8"></script>
<script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js?v=${staticVersion}"></script>
<script>
var contextPath="${contextPath}";
function deleteAuthority(aId){
	var txt = "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		$.ajax({
			async:false,
			data:{id:aId},
			dataType: "json",
			url:contextPath+"/admin/admin/deleteAthrty",
			type:"POST",
			success:function(data) {
	            if(data.error){
	            	alert(data.errorMessage);
	            }else{
	            	alert('删除成功');
	            	window.location.href=contextPath+"/admin/admin/athrtyList";
	            }
	        }, 
	        error:function(){
	            console.log("服务器繁忙...");
	        }
		});
	}})
}

function showAuthorityPop(channelId){
	var txt = "权限名称：<input type=\"text\" name=\"a_name\" class=\"inputBox\" style=\"width:120px;\"><br>"
			+ "权限路径：<input type=\"text\" name=\"a_url\" class=\"inputBox\" style=\"width:120px;\" ><br>"
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		var a_name = $("input[name='a_name']").val();
		var a_url = $("input[name='a_url']").val();
		if(a_name == null || a_name.length == 0){
			alert('请输入权限名称');
			return; 
		}
		if(a_url == null || a_url.length == 0){
			alert('请输入权限路径');
			return; 
		}
		$.ajax({
			async:false,
			data:{authority_name:a_name,authority_url:a_url},
			dataType: "json",
			url:contextPath+"/admin/admin/saveAthrty",
			type:"POST",
			success:function(data) {
	            if(data.error){
	            	if(data.errorCode!='SUCCESS'){
	            		alert('服务器繁忙...');
	            	}
	            }else{
	            	alert('保存成功');
	            	window.location.href=contextPath+"/admin/admin/athrtyList";
	            }
	        }, 
	        error:function(){
	            console.log("服务器繁忙...");
	        }
		});
	}})
}

function updateAuthority(item){
	var authority_name=$(item).parent().parent().parent().find("td").eq(1);
	var html = authority_name.html();
	var id = $(item).attr('dataId');
	authority_name.html("<input type='text' name='authority_name' onblur='updateAuthority2(this,"+id+");' value='"+html+"'>");
	$(item).removeAttr("onclick");
}

function updateAuthority2(item,id){
	var value=$(item).val();
	$.ajax({
		async:false,
		data:{authority_name:value,id:id},
		dataType: "json",
		url:contextPath+"/admin/admin/updateAthrty",
		type:"POST",
		success:function(data) {
            if(data.error){
            	if(data.errorCode!='SUCCESS'){
            		alert('服务器繁忙...');
            	}
            }else{
            	window.location.href=contextPath+"/admin/admin/athrtyList";
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