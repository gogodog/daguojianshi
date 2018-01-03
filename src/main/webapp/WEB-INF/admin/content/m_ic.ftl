<!DOCTYPE html>
<html lang="en">
<head>
<#include "/admin/common/head_title.ftl">
<script src="/admin/js/jquery-1.11.1.min.js"></script>
<script src="/admin/js/support-fileupload.js"></script>
<script src="/admin/js/ajaxfileupload.js"></script>
<script src="/admin/js/validation/jquery.validate.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">内容管理</a>><a href="">首页配置管理</a>><a href="">修改首页配置</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改首页配置</h3>
			</div>
			<div class="public-content-cont">
			<form action="${contextPath}/admin/midxcfg/save" method="post" enctype="multipart/form-data" id="indexConfigForm">
			    <fieldset>    
			    <input type="hidden" name="id" value="${mIndexConfig.id}">
			    <input type="hidden" name="status" value="${mIndexConfig.status}">
			    <input type="hidden" name="type" value="${mIndexConfig.type}">
			    <input type="hidden" name="position" value="${mIndexConfig.position}">
			    <input type="hidden" name="sort" value="${mIndexConfig.sort}">
			    <input type="hidden" name="pictures" value="${mIndexConfig.pictures}">
			    
		        <div class="form-group">
				    <label for="">位置：</label>
				    类型：
				    <select disabled="disabled">
				      <option>${mIndexConfig.type.value}</option>
				    </select>
				    位置：
				    <select disabled="disabled">
				      <option>${mIndexConfig.position}</option>
				    </select>
				    排序：
				    <select disabled="disabled">
				      <option>${mIndexConfig.sort}</option>
				    </select>
			    </div>
			    <#if mIndexConfig.position!='ad'>
				<div class="form-group">
					<label for="">文章id</label>
					<input class="form-input-txt" type="text" name="aid" value="${(mIndexConfig.aid)!''}"/>
				</div>
				</#if>
				<div class="form-group">
					<label for="">标题</label>
					<input class="form-input-txt" type="text" name="title" value="${(mIndexConfig.title)!''}" maxlength="255"/>
				</div>
				<div class="form-group">
			        <label for="">精简内容</label>
			        <textarea style="margin: 0px; width: 690px; height: 125px;" id="sub_content" name="sub_content" maxlength="500">${(mIndexConfig.sub_content)!''}</textarea>
		        </div>
		        <#if mIndexConfig.position=='ad'>
				<div class="form-group">
				    <label for="">链接</label>
				    <input class="form-input-txt" type="text" name="link" value="${(mIndexConfig.link)!''}" maxlength="255" />
			    </div>
			    </#if>
			    <div class="form-group">
			        <label for="">展示图片</label>
			        <input style="margin-top:9px" type="radio" value="1" name="picm" checked>本地上传
				    <input style="margin-top:9px" type="radio" value="2" name="picm">已有图片链接
			        <div id="showImage">
			           <#if mIndexConfig.pics??>
			             <#list  mIndexConfig.pics as url>
			               <img src="${imageContextPath}${url}" style="width:200px;height:200px;"> 
			             </#list>
			           </#if>
			        </div>
			        <div id="showImage2"></div>
			        <div class="file"><input type="file" class="form-input-file" id="uploadImage" name="uploadImage" multiple/>选择文件</div>
			        <div class="file"><input type="button" class="form-input-file" id="buttonUpload" onClick="return ajaxFileUpload();">上传</div>
		        </div>
		        <div class="form-group" style="display:none">
		            <label for=""></label>
		            <textarea style="margin: 0px; width: 690px; height: 125px;" maxlength="500" id="piclinkVal"></textarea>
	            </div>
				<div class="form-group" style="margin-left:150px;">
					<input type="button" class="sub-btn" value="提  交" onclick="doSubmit()"/>
					<#if mIndexConfig.position!='ad'>
					  <input type="button" class="sub-btn" value="选择文章" id="showArticles"/>
					</#if>
				</div>
				</fieldset>
			</form>
		  </div>
		  
		  <div id="hiddenArea">
	        
		    <br><br>
			
			<div class="public-content-cont" id="articlesDiv" style="display:none">
			     <p style="margin-bottom:10px">
	                <label>文章id:</label><input type="text" id="maid" />&nbsp;&nbsp;
	                <label>文章标题:</label><input type="text" id="mtitle" />&nbsp;&nbsp;
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
		               <td><input type="radio" value="id" onclick="chooseArticle(this)" name="articleRadio"/></td>
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
		  
		</div>
	</div>
</body>
<script>
var contextPath='${contextPath}';
var currentPage=1;
var type='${mIndexConfig.type}';
var position='${mIndexConfig.position}';
var sort='${mIndexConfig.sort}';
var chooseType="radio";
var imagePath="${imageContextPath}"

function ajaxFileUpload()
{
    var imageContextPath="${imageContextPath}";
    var uploadFileName="m_index_config";
    $.ajaxFileUpload
    (
        {
        	async:false,
            url:contextPath+'/admin/static/ajaxUpload?imagePath='+uploadFileName,//这个是要提交到上传的php程序文件
            secureuri:false,
            fileElementId:'uploadImage',//这里是你文件上传input框的id
            dataType: 'json',
            success: function (result)
            {
                if(typeof(result.error) != 'undefined')
                {
                    if(result.error != '')
                    {
                        alert(result.errorMessage);//如有错误则弹出错误
                    }else
                    {
                    	var results=result.list;
                    	var divImages="";
                    	var pictures="";
                    	for(var i=0;i<results.length;i++){
                    		var accessPath=imageContextPath+results[i].minImageUrl;
                    		divImages+="<img src=\""+accessPath+"\" style=\"width:200px;height:200px;\">";
                    		pictures+="<input type=\"hidden\" name=\"pictures\" value=\""+results[i].minImageUrl+"\">";
                    	}
                    	$("#showImage").html(divImages);
                    	$("#pictures").html(pictures);
                    }
                }
            },
            error: function (result, status, e)
            {
                alert(e);
            }
        }
    )
}

$("input[name='picm']").change(function(){
	var checkeVal=$(this).val();
	if(checkeVal=='2'){
		$("#piclinkVal").parent().show();
		$("#uploadImage").parent().hide();
		$("#buttonUpload").parent().hide();
	}else if(checkeVal=='1'){
		$("#piclinkVal").parent().hide();
		$("#uploadImage").parent().show();
		$("#buttonUpload").parent().show();
	}
})

function doSubmit(){
	var maid = $("input[name='aid']").val();
	var mtitle = $("input[name='title']").val();
	var mlink = $("input[name='link']").val();
	var m_sub_content = $("#sub_content").val();
	var pictures = $("input[name='pictures']").val();
	var status = $("input[name='status']").val();
	var id = $("input[name='id']").val();
	if($.trim(mtitle)==''||$.trim(mtitle)==null){
		alert('标题不能为空');
		return;
	}
    if(position=='ad' && mlink==''){
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
            	alert('修改成功');
            	window.location.href="/admin/midxcfg/list";
            }
        }, 
        error:function(){
            console.log("服务器繁忙...");
        }
	});
}

$("#conditionButton").click(function(){
	currentPage = 1; 
	var aid = $("#maid").val();
	var atitle = $("#mtitle").val();
	getArticles(type,position,currentPage,atitle,aid);
});

function removeTr(tr){
	var allTrs=$(tr).parent().children("tr");
	for(var i=0;i<allTrs.length;i++){
		if(i>1){
			allTrs.get(i).remove();
		}
	}
}

function rplc(str){
	return new RegExp(str,"gm");
} 

function formatDate(nS) {     
    return new Date(parseInt(nS)).toLocaleString().replace("/年|月/g", "-").replace("/日/g", " ");      
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

function getArticles(type,position,page,title,aid){
	$("#indexConfigForm")[0].reset();;
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

$("#showArticles").click(function(){
	$("#articlesDiv").show();
})

function chooseArticle(item){
	var tr = $(item).parent().parent();
	var articleId = $(tr).children("td:eq(1)").html();
	var title = $(tr).children("td:eq(2)").html();
	var sub_content = $(tr).children("td:eq(3)").attr("dataVal");
	var showPic = $(tr).children("td:eq(4)").attr("dataVal");
	var pictures = $(tr).children("td:eq(5)").attr("dataVal");
	
	$("input[name='aid']").val(articleId);
	$("input[name='title']").val(title);
	$("#sub_content").val(sub_content);
	
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
	$("#showImage2").html(images); 
	$("#showImage").hide(); 
}
</script>
</html>