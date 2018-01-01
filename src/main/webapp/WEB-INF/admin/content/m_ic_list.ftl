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
		<div class="public-nav">您当前的位置：<a href="">管理首页</a>><a href="">内容管理</a><a href="">首页配置管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
			<h3 style="display: inline-block;">首页配置管理列表</h3>
				<div class="public-content-right fr">
				<!-- 
				<a href="/admin/idxcfg/indexConfig" 
				   style="height: 24px; width: 70px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加配置</a>-->
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
					     		<a href="javascript:void(0)" onclick="updateConfig(this,'${config.aid}','${config.id}','${config.status}','${config.pictures}');">修改</a>
					     		<a href="javascript:void(0)" onclick="deleteIndexConfig('${config.id}','${config.type}');">删除</a>
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
					
					<div class="public-content-cont">
					     <p style="margin-bottom:10px">
			                <label>文章id:</label><input type="text" id="aid" />&nbsp;&nbsp;
			                <label>文章标题:</label><input type="text" id="atitle" />&nbsp;&nbsp;
			                <input type="button" value="查询" id="conditionButton" class="select-btn"/>
			                <input type="hidden" id="pictures">
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
				    <input type="hidden" id="configId"> 
				    <fieldset>    
					<div class="form-group">
						<label for="">文章id</label>
						<input class="form-input-txt" type="text" name="maid" readOnly/>
					</div>
					<div class="form-group">
						<label for="">标题</label>
						<input class="form-input-txt" type="text" name="mtitle"  maxlength="50"/>
					</div>
					<div class="form-group">
					    <label for="">链接</label>
					    <input class="form-input-txt" type="text" name="mlink"  maxlength="255"/>
				    </div>
					<div class="form-group">
				        <label for="">精简内容</label>
				        <textarea style="margin: 0px; width: 690px; height: 125px;" id="m_sub_content" name="m_sub_content" maxlength="255"></textarea>
			        </div>
				    <div class="form-group">
				        <label for="">展示图片</label>
				        <div id="showImage2">
				        </div>
					    <input style="margin-top:9px" type="radio" value="2" name="picm" checked>从文章图片选择
					    <input style="margin-top:9px" type="radio" value="1" name="picm">本地上传
					    <div id="showImage">
				        </div>
				        <div class="file" style="display:none"><input type="file" class="form-input-file" id="uploadImage" name="uploadImage" multiple/>选择文件</div>
				        <div class="file" style="display:none"><input type="button" class="form-input-file" id="buttonUpload" onClick="return ajaxFileUpload();">上传</div>
			        </div>
			        <div class="form-group" style="display:none">
			            <label for=""></label>
			            <textarea style="margin: 0px; width: 690px; height: 125px;" maxlength="500" id="piclinkVal"></textarea>
		            </div>
					<div class="form-group">
					    <label for="">是否上架：</label>
					    <input style="margin-top:9px" type="checkbox" id="upDownStatus" value="DOWN"/>
				    </div>
					<div class="form-group" style="margin-left:150px;">
						<input type="button" class="sub-btn" value="提  交" onclick="doSubmit();"/>
					</div>
					</fieldset>
					</form>
				</div>
				
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
	
	function changePosition(item){
		var position = $(item).val();
		if(position=='ad'){
			getAddSortHtml(adNum);
			currentPage=1;
			removeTr($("#hiddenArticleTr"));
		}else if(position=='first'){
			getAddSortHtml(firstNum);
			getArticles(type,'second',1);
		}else if(position=='second'){
			getAddSortHtml(secondNum);
			getArticles(type,'second',1);
		}
		var sort = $("#addSort").val();
		getConfigList(type,position,sort);
		cleanChooseArticle();
	}
	
	function changeSort(item){
		var sort = $(item).val();
		var position = $("#addPosition").val();
		getConfigList(type,position,sort);
		cleanChooseArticle();
	}
	
	function cleanChooseArticle(){
		$("#indexConfigDiv").hide();
		$("#indexConfigForm")[0].reset();
	}
	
	function articleVal(item){
		var value = $(item).val();
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
		$("input[name='mlink']").val("//www.cwillow.com/show/"+articleId);
		
		var position = $("#addPosition").val();
		if((type=='HISTORY'||type=='GEOGRAPHY')&&position=='first'){
			chooseType = "checkbox"
		}else{
			chooseType = "radio"
		}
		
		var images = "<img src=\""+imagePath+showPic+"\" style=\"width:200px;height:200px;\">&nbsp;";
		images += "<input type=\""+chooseType+"\" name=\"choosePic\" value=\""+showPic+"\" onchange=\"choosePictures();\" checked>";

		var picArray = pictures.split(",");
		for(var i=0;i<picArray.length;i++){
			images+="<img src=\""+imagePath+picArray[i]+"\" style=\"width:200px;height:200px;\">&nbsp;";
			images += "<input type=\""+chooseType+"\" name=\"choosePic\" value=\""+picArray[i]+"\" onchange=\"choosePictures();\" >";
		}
		$("#showImage").html(images); 
	}
	
	function choosePictures(){
		var pics = "";
		var index = 0;
		var length = $("input[name='choosePic']:checked").length;
		$("input[name='choosePic']:checked").each(function(){
			pics += $(this).val()
			if(index++!=length-1){
				pics += ",";
			}
		});
		$("#pictures").val(pics);
	}
	
	$("#upDownStatus").click(function(){
		if($('#upDownStatus').is(':checked')) {
			   $("#upDownStatus").val("UP");
		}else{
			$("#upDownStatus").val("DOWN");
		}
	});
	
	<!-- 
	$("input[name='picm']").change(function(){
		if($(this).val()==1){
			$("#showImage").hide();
		}
	});
	-->
	
	function doSubmit(){
		var maid = $("input[name='maid']").val();
		var mtitle = $("input[name='mtitle']").val();
		var mlink = $("input[name='mlink']").val();
		var m_sub_content = $("#m_sub_content").val();
		var pictures = $("#pictures").val();
		var status = $("#upDownStatus").val();
		var position = $("#addPosition").val();
		var sort = $("#addSort").val();
		var id = $("#configId").val();
		
		if($.trim(mtitle)==''||$.trim(mtitle)==null){
			alert('标题不能为空');
			return;
		}
		if($.trim(mlink)==''||$.trim(mlink)==null){
			alert('链接不能为空');
			return;
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
		param['id'] = $.trim(id);
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
	
	function updateConfig(item,aid,id,status,pictures){
		cleanChooseArticle();
		$("#indexTable").children("tbody").children("tr").each(function(){
			$(this).attr("bgcolor","white");
		})
		
		$("#hiddenArea").hide();
		$("#indexConfigDiv").show();
		$("#addMIndexConfig").click(function(){
			addMIndexConfigClick();
		});
		
		var title = $("#c_tr_"+id).children("td:eq(2)").html();
		var link = $("#c_tr_"+id).children("td:eq(5)").html();
		var sub_content = $("#c_tr_"+id).children("td:eq(6)").html();
		var position = $("#c_tr_"+id).children("td:eq(1)").html();
		var sort = $("#c_tr_"+id).children("td:eq(3)").html();
		
		$("input[name='maid']").val(aid);
		$("input[name='mtitle']").val(title);
		$("input[name='mlink']").val(link);
		$("#m_sub_content").val(sub_content);
		$("#upDownStatus").val(status);
		if(status=='UP'){
			$("#upDownStatus").attr("checked","checked");
		}
		$("#pictures").val(pictures);
		var picArray = pictures.split(",");
		var picturesHtml = '';
		for(var i=0;i<picArray.length;i++){
			picturesHtml+="<img src=\""+imagePath+picArray[i]+"\" style=\"width:200px;height:200px;\">&nbsp;"
		}
		$("#showImage2").html(picturesHtml);
		
		$("#addPosition").val(position);
		$("#addSort").val(sort);
	    $("#configId").val(id);
	}
	</script>
</body>
</html>