<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<link rel="stylesheet" type="text/css" href="/admin/css/xcConfirm.css"/>
<script src="/admin/js/jquery-1.11.1.min.js"></script>
<script src="/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
<link href="/admin/plugins/cropper/css/cropper.min.css" rel="stylesheet">
<link href="/admin/plugins/cropper/css/bootstrap.min.css" rel="stylesheet">
<script src="/admin/plugins/cropper/js/bootstrap.min.js"></script>
<script src="/admin/plugins/cropper/js/cropper.min.js"></script>
<script src="/admin/plugins/cropper/js/main.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">首页配置管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;font-size:100%;margin-top: 0px;">首页配置管理列表</h3>
				<div class="public-content-right fr">
				<a href="javascript:void(0)" id = "addMIndexConfig"
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加配置</a>  
			</div>
			</div>
			
			<div class="public-content-cont">
			<form id="selectForm" action="${contextPath}/admin/midxcfg/list" method="post">
		      <p style="margin-bottom:10px">
		        <label>位置:</label><select name="type"><#list types as type>
	              <option <#if condition.type?? && condition.type == type>selected</#if> value="${type}">${type.value}</option>
	           </#list></select>&nbsp;&nbsp;
		        <label>状态:</label><select name="status"><option value="">全部</option><#list upDownStatus as status><option value="${status}" <#if condition.status.key=="${status.key}">selected</#if>>${status.value}</option></#list></select>&nbsp;&nbsp;
		        <input type="submit" value="查询" name="conditionButton" class="select-btn">
		      </p>
		    </form>
				<table class="public-cont-table" id="indexTable">
					<tr>
						<th style="width:8%">类型</th>
						<th style="width:8%">位置</th>
						<th style="width:14%">标题</th>
						<th style="width:8%">排序</th>
						<th style="width:8%">状态</th>
						<th style="width:14%">链接</th>
						<th style="width:20%">精简内容</th>
						<th style="width:20%">操作</th>
					</tr>
					<#list list as config>
					  <tr id="c_tr_${config.id}">
					     <td>${config.type.value}</td>
					     <td>${config.position}</td>						
					     <td>${config.title}</td>
					     <td>${config.sort}</td>
					     <td>${config.status.value}</td>
					     <td>${config.link}</td>
					     <td>${config.sub_content}</td>
					     <td>
					     	<div class="table-fun">
					     		<a href="/admin/midxcfg/selectById?id=${config.id}">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteIndexConfig('${config.id}','${config.type}');">删除</a>
					     		<a href="javascript:void(0)" onclick="updateStatus('${config.status}','${config.id}');">
					     		  <#if config.status=='UP'>下架<#elseif config.status=='DOWN'>上架</#if>
					     	    </a>
					     	</div>
					     </td>
				     </tr>
				   </#list>
				</table>
				
				<div id="hiddenArea" style="display:none">
				   <br><br>
				   位置：<select id="addPosition" onchange="changePosition(this)">
				          <#list positions as position>
				             <option value="${position}" <#if position=='second'>selected</#if>>${position}</option>
				          </#list>
				        </select>
				   排序：<select id="addSort" onchange="changeSort(this)">
				        </select>
				        
				    <br><br>
					
					<div class="public-content-cont" id="articlesDiv">
					     <p style="margin-bottom:10px">
			                <label>文章id:</label><input type="text" id="aid" />&nbsp;&nbsp;
			                <label>文章标题:</label><input type="text" id="atitle" />&nbsp;&nbsp;
			                <input type="button" value="查询" id="conditionButton" class="select-btn"/>
			             </p>
					     <table class="public-cont-table">
					        <tr>
					          <th style="width:10%">选择</th>
				              <th style="width:10%">文章id</th>
					          <th style="width:15%">文章标题</th>
					          <th style="width:25%">文章内容</th>
					          <th style="width:10%">文章展示时间</th>
					          <th style="width:10%">作者</th>
					          <th style="width:10%">创建时间</th>
					          <th style="width:10%">修改时间</th>
				            </tr>
				            <tr id="hiddenArticleTr" style="display:none">
				               <td><input type="radio" value="id" onclick="articleVal(this)" name="articleRadio"/></td>
					           <td>id</td>			
				               <td>title</td>						
				               <td dataVal="sub_total_content">sub_content</td>
				               <td dataVal="show_pic">show_time</td>
				               <td dataVal="pictures">author</td>
				               <td>create_time</td>
				               <td>update_time</td>
				             </tr>
				         </table>
				         <div class="page" id="articlePage" style="display:none">
				  	       <a href="javascript:void(0)">上一页</a>
				  	       <a href="javascript:void(0)">下一页</a>
				         </div>
				     </div>
				     
				     <br>
				</div>
				
				<div class="public-content-cont" id="indexConfigDiv" style="display:none;">
				<form action="${contextPath}/admin/idxcfg/saveOrUpdate" method="post" enctype="multipart/form-data" id="indexConfigForm">
				    <fieldset>    
					<div class="form-group">
						<label for="">文章id</label>
						<input class="form-input-txt" type="text" name="maid" readOnly/>
					</div>
					<div class="form-group">
						<label for="">标题</label>
						<input class="form-input-txt" type="text" name="mtitle"  maxlength="50"/>
					</div>
					<div class="form-group" style="display:none">
					    <label for="">链接</label>
					    <input class="form-input-txt" type="text" name="mlink"  maxlength="255"/>
				    </div>
					<div class="form-group">
				        <label for="">精简内容</label>
				        <textarea style="margin: 0px; width: 690px; height: 125px;" id="m_sub_content" name="m_sub_content" maxlength="255"></textarea>
			        </div>
				    <div class="form-group">
				        <label for="">展示图片</label>
					    <div id="showImage">
				        </div>
			        </div>
			        <div class="form-group" style="display:none">
			            <label for=""></label>
			            <textarea style="margin: 0px; width: 690px; height: 125px;" maxlength="500" id="piclinkVal"></textarea>
		            </div>
					<div class="form-group" style="margin-left:150px;">
						<input type="button" class="sub-btn" value="提  交" onclick="doSubmit();"/>
					</div>
					</fieldset>
					</form>
				</div>
				
				<!-- 上传图片 begin -->
		          <div class="container" id="crop-avatar" style="display:none">
		          <h5>新增展示图片</h5>
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
		          <!-- 上图图片 end -->
				
			</div>
		</div>
	</div>
	<script>
	var contextPath="${contextPath}";
	var type="${condition.type}";
	var currentPage = 1;
	var adNum = 0;
	var firstNum = 0;
	var secondNum =0;
	var imagePath = "${imagePath}";
	var chooseType = "";
	
	function deleteIndexConfig(id,type){
		var txt=  "您确定要删除这条数据吗？";
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
			window.location.href=contextPath+"/admin/midxcfg/delete?id="+id+"&type="+type;
		}})
	}
	
	$("#addMIndexConfig").click(function(){
		addMIndexConfigClick();
	})
	
	function addMIndexConfigClick(){
		getConfigValue();
		getConfigList(type,'second',1);
		getArticles(type,'second',1);
		$("#hiddenArea").show();
		$("#addMIndexConfig").unbind("click");
	}
	
	function getConfigList(type,position,sort){
		$("#indexTable").find("tr").attr("bgcolor","white");
		$.ajax({
			async:false,
			data:{type:type,position:position,sort:sort},
			dataType: "json",
			url:contextPath+"/admin/midxcfg/listMIndexConfig",
			type:"POST",
			success:function(data) {
	            if(data.error){
	            	if(data.errorCode!='SUCCESS'){
	            		alert(data.errorMessage);
	            	}
	            }else{
	            	var mIndexConfigList = data.objects;
	            	for(var i=0;i<mIndexConfigList.length;i++){
	            		var id = mIndexConfigList[i].id;
	            		var obj =$("#c_tr_"+id);
	            		if(obj.length>0){
	            			$("#c_tr_"+id).attr("bgcolor","#6CB98F");
	            		}
	            	}
	            }
	        }, 
	        error:function(){
	            console.log("服务器繁忙...");
	        }
		});
	}
	
	function getArticles(type,position,page,title,aid){
		$("#crop-avatar").hide();
		$("#indexConfigDiv").hide();
		$("#articlesDiv").show();
		$("input[name='maid']").parent().show();
		$("input[name='mlink']").val('');
		$("input[name='mlink']").parent().hide();
		cleanChooseArticle();
		$.ajax({
			async:false,
			data:{type:type,position:position,currentPage:page,title:title,aid:aid},
			dataType: "json",
			url:contextPath+"/admin/midxcfg/getArticles",
			type:"POST",
			success:function(data) {
	            if(data.error){
	            	if(data.errorCode!='SUCCESS'){
	            		alert(data.errorMessage);
	            	}
	            }else{
	            	var objects = data.objects;
	            	var articlesList = objects.list;
	            	removeTr($("#hiddenArticleTr"));
	            	if(articlesList!=null && articlesList.length>0){
	            		particlesList(articlesList);
	            	}
	            	var hasNext = objects.hasNext;
	            	var hasPrev = objects.hasPrev;
	            	if(hasNext){
	            		$("#articlePage").show();
	            		$("#articlePage").children("a:eq(1)").show();
	            		$("#articlePage").children("a:eq(1)").unbind("click");
	            		$("#articlePage").children("a:eq(1)").click(function(){
	            			currentPage+=1;
	            			getArticles(type,position,currentPage,title,aid);
	            		});
	            	}else{
	            		$("#articlePage").children("a:eq(1)").hide();
	            		$("#articlePage").children("a:eq(1)").unbind("click");
	            	}
	            	if(hasPrev){
	            		$("#articlePage").show();
	            		$("#articlePage").children("a:eq(0)").show();
	            		$("#articlePage").children("a:eq(0)").unbind("click");
	            		$("#articlePage").children("a:eq(0)").click(function(){
	            			currentPage-=1;
	            			getArticles(type,position,currentPage,title,aid);
	            		});
	            	}else{
	            		$("#articlePage").children("a:eq(0)").hide();
	            		$("#articlePage").children("a:eq(0)").unbind("click");
	            	}
	            }
	        }, 
	        error:function(){
	            console.log("服务器繁忙...");
	        }
		});
	}
	
	function rplc(str){
		return new RegExp(str,"gm");
	} 
	
	function removeTr(tr){
		var allTrs=$(tr).parent().children("tr");
		for(var i=0;i<allTrs.length;i++){
			if(i>1){
				allTrs.get(i).remove();
			}
		}
	}
	
	function particlesList(list){
		var content = '';
		var template = $("#hiddenArticleTr").html();
		for(var i=0;i<list.length;i++){
			var item = list[i];
			content+="<tr>"
			content+=template.replace(rplc("title"),item.title)
					.replace(rplc("id"),item.id)
					.replace(rplc("sub_content"),item.sub_content.length>30?item.sub_content.substring(0,40)+"...":item.sub_content)
					.replace(rplc("sub_total_content"),item.sub_content)
					.replace(rplc("show_time"),formatDate(item.show_time))
					.replace(rplc("show_pic"),item.showPic)
					.replace(rplc("pictures"),item.pictures)
					.replace(rplc("author"),item.author)
					.replace(rplc("create_time"),formatDate(item.create_time))
					.replace(rplc("update_time"),formatDate(item.update_time));
			content+="</tr>";
		}
		$("#hiddenArticleTr").after(content);
	}
	
	
	function formatDate(nS) {     
	       return new Date(parseInt(nS)).toLocaleString().replace("/年|月/g", "-").replace("/日/g", " ");      
    }  
	
	$("#conditionButton").click(function(){
		var aid = $("#aid").val();
		var atitle = $("#atitle").val();
		var position = $("#addPosition").val();
		currentPage = 1; 
		getArticles(type,position,currentPage,atitle,aid);
	})
   
	function getConfigValue(){
		$.ajax({
			async:false,
			data:{type:type},
			dataType: "json",
			url:contextPath+"/admin/midxcfg/getConfigValue",
			type:"POST",
			success:function(data) {
	            if(data.error){
	            	if(data.errorCode!='SUCCESS'){
	            		alert(data.errorMessage);
	            	}
	            }else{
	            	var dto = data.objects;
	                adNum = dto.ad;
	                firstNum = dto.first;
	            	secondNum = dto.second;
	            	getAddSortHtml(secondNum)
	            }
	        }, 
	        error:function(){
	            console.log("服务器繁忙...");
	        }
		});
	}
	
	function getAddSortHtml(num){
		$("#addSort").empty();
		var content = '';
		for(var i=1;i<=num;i++){
			content+="<option value='"+i+"'>"+i+"</option>";
		}
		$("#addSort").append(content);
	}
	
	function chooseAd(){
		currentPage=1;
		removeTr($("#hiddenArticleTr"));
		$("#indexConfigDiv").show();
		$("#crop-avatar").show();
		$("#articlesDiv").hide();
		$("input[name='maid']").parent().hide();
		$("input[name='mlink']").parent().show();
		$("input[name='mlink']").val('');
		$("#articleBtn").hide();
	}
	
	function changePosition(item){
		var position = $(item).val();
		cleanChooseArticle();
		if(position=='ad'){
			getAddSortHtml(adNum);
			chooseAd();
		}else if(position=='first'){
			getAddSortHtml(firstNum);
			getArticles(type,'second',1);
		}else if(position=='second'){
			getAddSortHtml(secondNum);
			getArticles(type,'second',1);
		}
		var sort = $("#addSort").val();
		getConfigList(type,position,sort);
	}
	
	function changeSort(item){
		var sort = $(item).val();
		var position = $("#addPosition").val();
		getConfigList(type,position,sort);
		cleanChooseArticle();
	}
	
	function cleanChooseArticle(){
		$("#crop-avatar").hide();
		$("#indexConfigDiv").hide();
		$("#indexConfigForm")[0].reset();
	}
	
	function articleVal(item){
		$("#crop-avatar").show();
		$("#indexConfigDiv").show();
		chooseArticle(item)
	}
	
	function chooseArticle(item){
		var tr = $(item).parent().parent();
		var articleId = $(tr).children("td:eq(1)").html();
		var title = $(tr).children("td:eq(2)").html();
		var sub_content = $(tr).children("td:eq(3)").attr("dataVal");
		var showPic = $(tr).children("td:eq(4)").attr("dataVal");
		var pictures = $(tr).children("td:eq(5)").attr("dataVal");
		
		$("input[name='maid']").val(articleId);
		$("input[name='mtitle']").val(title);
		$("#m_sub_content").val(sub_content);
		
		var position = $("#addPosition").val();
		if((type=='HISTORY'||type=='GEOGRAPHY')&&position=='first'){
			chooseType = "checkbox"
		}else{
			chooseType = "radio"
		}
		
		var images = "<img src=\""+imagePath+showPic+"\" style=\"width:200px;height:200px;\">&nbsp;";
		images += "<input type=\""+chooseType+"\" name=\"choosePic\" value=\""+showPic+"\" >";

		var picArray = pictures.split(",");
		for(var i=0;i<picArray.length;i++){
			images+="<img src=\""+imagePath+picArray[i]+"\" style=\"width:200px;height:200px;\">&nbsp;";
			images += "<input type=\""+chooseType+"\" name=\"choosePic\" value=\""+picArray[i]+"\" >";
		}
		$("#showImage").html(images); 
		$("#crop-avatar").show();
	}
	
	
	function doSubmit(){
		var maid = $("input[name='maid']").val();
		var mtitle = $("input[name='mtitle']").val();
		var mlink = $("input[name='mlink']").val();
		var m_sub_content = $("#m_sub_content").val();
		var position = $("#addPosition").val();
		var sort = $("#addSort").val();
		if($.trim(mtitle)==''||$.trim(mtitle)==null){
			alert('标题不能为空');
			return;
		}
	    if(position=='ad' && mlink==''){
	    	alert('链接不能为空');
			return;
	    }
	    
	    var pictures='';
	    var pics = $("input[name='choosePic']:checked");
	    var needPics = 1;
	    if(position=='first' && (type=='HISTORY'||type=='GEOGRAPHY')){
	    	needPics = 3;
	    }
	    if(pics==null||pics.length!=needPics){
	    	alert('请选择正确数量的图片');
	    	return;
	    }
	    for(var i=0;i<pics.length;i++){
	    	pictures+=$(pics[i]).val();
	    	if(i!=pics.length-1){
	    		pictures+=","
	    	}
	    }
	    
		var param = {};
		param['type'] = type;
		param['position'] = position;
		param['sort'] = sort;
		param['status'] = status;
		param['aid'] = maid;
		param['pictures'] = pictures;
		param['title'] =$.trim(mtitle);
		param['sub_content'] = $.trim(m_sub_content);
		param['link'] = $.trim(mlink);
		$.ajax({
			async:false,
			data:JSON.stringify(param),
			dataType: "json",
			contentType:"application/json",
			url:"/admin/midxcfg/save",
			type:"POST",
			success:function(data) {
	            if(data.error){
	            	if(data.errorCode!='SUCCESS'){
	            		alert(data.errorMessage);
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
	
	function updateStatus(status,id){
		var txt = "";
		var toStatus = "";
		if(status == 'DOWN'){
			txt = "您确定要上架吗？";
			toStatus = 'UP';
		}else if(status == 'UP'){
			txt = "您确定要下架吗？";
			toStatus = 'DOWN';
		}
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
			changeStatus(toStatus,id);
		}})
	}
	
	function changeStatus(status,id){
		$.ajax({
			async:false,
			data:{status:status,id:id},
			dataType: "json",
			url:contextPath+"/admin/midxcfg/updateStatus",
			type:"POST",
			success:function(data) {
	            if(data.error){
	            	if(data.errorCode!='SUCCESS'){
	            		alert(data.errorMessage);
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
	
	function uploadBase64(){
		var base64 = $('#avatar')[0].src;
		if(!base64 || base64.indexOf("base64") <= 0){
			alert('请选择图片');
			return;
		}
		var contextPath="${contextPath}";
	    var imageContextPath="${imageContextPath}";
	    var uploadFileName="mindex";
		var data = {};
		data['base64'] = base64;
		$.ajax(contextPath+'/admin/static/ajaxUploadBase64?imagePath='+uploadFileName+"&dapt=true", {
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
					var imageUrls = $("#showImage").html();
					debugger;
					for(var i=0;i<results.length;i++){
						var accessPath=imagePath+results[i].minImageUrl;
						imageUrls+="<img src=\""+accessPath+"\" style=\"width:200px;height:200px;\">&nbsp;"
						imageUrls+= "<input type=\""+chooseType+"\" name=\"choosePic\" value=\""+results[i].minImageUrl+"\">";
					}
					$("#showImage").html(imageUrls); 
				}
			}
	    },
	    complete:function(){$("#avatar").attr('src',contextPath+'/admin/images/selimg.png');},
	    error: function (result, status, e){alert(e);}
	  });
	}
	</script>
</body>
</html>