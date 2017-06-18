<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="${contextPath}/admin/css/xcConfirm.css"/>
<script src="${contextPath}/admin/js/jquery-1.11.1.min.js"></script>
<script src="${contextPath}/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">文章推荐管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">文章推荐列表</h3>
				<div class="public-content-right fr">
				<a href="${contextPath}/admin/recommedArticlescrap" 
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加推荐</a>
			</div>
			</div>
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:5%">ID</th>
						<th style="width:20%">文章标题</th>
						<th style="width:40%">文章内容</th>
						<th style="width:5%">排序</th>
						<th style="width:5%">状态</th>
						<th style="width:25%">操作</th>
					</tr>
					<#list recommedArticlescrapList as recommedArticlescrap>
					  <tr>
					     <td>${recommedArticlescrap.id}</td>
					     <td>${recommedArticlescrap.title}</td>		
					     <td>${recommedArticlescrap.sub_content}</td>		
					     <td>${recommedArticlescrap.recommend.sort}</td>	
					     <td> 
					          <#if recommedArticlescrap.recommend.status == 1>
					            上架
                              </#if> 
					          <#if recommedArticlescrap.recommend.status == 0>
					            下架
                              </#if> 
                         </td>
					     <td>
					     	<div class="table-fun-1">
					     	    <a href="${contextPath}/admin/articlescrap?articlescrapId=${recommedArticlescrap.id}">查看文章</a>
					     		<a href="javascript:void(0)" onclick="deleteRA('${recommedArticlescrap.id}');">删除</a>
					     		<a href="javascript:void(0)" onclick="updateStatus('${recommedArticlescrap.id}','${recommedArticlescrap.recommend.status}');">
                                   <#if recommedArticlescrap.recommend.status == 1>
                                      下架
                                   </#if>
                                   <#if recommedArticlescrap.recommend.status == 0>
                                      上架
                                   </#if>
					     		</a>
					     	</div>
					     </td>
				     </tr>
				   </#list>
				</table>
			</div>
		</div>
	</div>
	
<script>
var contextPath="${contextPath}";
function updateStatus(recommedArticlescrapId,status){
	if(status == '1'){
		status = 'DOWN';
	}else{
		status = 'UP';
	}
	$.ajax({
	    url:contextPath+"/admin/ajaxUpdateRAStatus",    
	    dataType:"json",   
	    async:false,
	    data:{"recommedArticlescrapId":recommedArticlescrapId,"status":status}, 
	    type:"GET",   
	    success:function(req){
	    	if(!req.error){
	    		window.location.href=contextPath+"/admin/recommedArticlescrapList";
	    	}else if(req.errorCode!='PARAM_ERROR'){
	    		alert(req.errorMessage);
	    	}
	    }, 
	    error:function(){
	        alert("系统繁忙，请稍后");
	       
	    }
	});
}

function deleteRA(recommedArticlescrapId){
	var txt=  "您确定要删除这条数据吗？";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
		window.location.href=contextPath+"/admin/deleteRecommedArticlescrap?recommedArticlescrapId="+recommedArticlescrapId;
	}})
}

</script>
</body>
</html>