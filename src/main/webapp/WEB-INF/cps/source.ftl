<#include "/cps/common/header.ftl">
<link href="${contextPath}/cps/css/prettyPhoto.css" rel="stylesheet" />
<link href="${contextPath}/cps/css/bootstrap-fileupload.min.css" rel="stylesheet" />
<body>
    <div id="wrapper">
        <#include "/cps/common/top.ftl">
        <#assign page_name='source'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">素材管理</h1>
                        <h1 class="page-subhead-line">系统至多保存${container}张素材展示.素材尺寸最大为${fileSize}M,系统支持多图上传，一次最多可上传${onceContainer}张</h1>
                </div>
                <div id="port-folio">
                          <ul id="filters" style="display:none">
								<li><span class="filter active" data-filter="zs dl ys">全部</span></li>
								<li><span class="filter active">/</span></li>
								<li><span class="filter" data-filter="zs">正史</span></li>
								<li><span class="filter">/</span></li>
								<li><span class="filter" data-filter="dl">地理</span></li>
								<li><span class="filter">/</span></li>
								<li><span class="filter" data-filter="ys">野史</span></li>
						  </ul>
						   <#list userPics.pics as pic>
						   <div class="col-md-2">
		                     <div class="portfolio-item ys mix_all" data-cat="ys" >
		                        <img src="${imageContextPath}${pic.url}" class="img-responsive" style="width:100%;height:145px;" />
		                        <div class="overlay">
	                              <p>
	                                  <span dataVal="${pic.url}" dataId="editName">${pic.name}</span>
	                              </p>
	                              <input type="checkbox" name="pics" value="${pic.url}" style="display:none">
	                          </div>
		                   </div>
		                   </div>
						   </#list>
						   <#if (!userPics.pics?? || userPics.pics?size<container) >  
			                <div class="col-md-2 ">
			                  <input type="file" id="uploadImage" name="uploadImage" style="display:none" multiple>
		                      <a class="preview btn btn-info" onclick="uploadFile(this);" href="javascrpt:void(0)"><i class="fa fa-plus fa-2x"></i></a>
		                    </div>
		                   </#if> 
		                   <#if (userPics.pics?? && userPics.pics?size>0) >  
			                <div class="col-md-2 ">
		                      <a class="preview btn btn-info" onclick="removeFile(this);" href="javascrpt:void(0)"><i class="fa fa-minus fa-2x"></i></a>
		                    </div>
		                    <button class="btn btn-primary" id="editName"><i class="glyphicon glyphicon-search"></i>Edit</button>
		                   </#if> 
		                </div>
			   </div>
		</div></div>
    <#include "/cps/common/f-static.ftl">
    <script src="${contextPath}/cps/js/jquery.prettyPhoto.js"></script>
    <script src="${contextPath}/cps/js/jquery.mixitup.min.js"></script>
    <script src="${contextPath}/cps/js/galleryCustom.js"></script>
    <script src="${contextPath}/cps/js/bootstrap-fileupload.js"></script>
    <script src="${contextPath}/cps/js/ajaxfileupload.js"></script>
    <script>
      var contextPath="${contextPath}";
      var userId="${userId}";
      function uploadFile(item){
          $(item).unbind("click");
          $("#uploadImage").click();
      }
      $("input[type=file]").change(function(e){
    	  ajaxFileUpload();
      });
      
      function ajaxFileUpload()
  	 {
  	    var imageContextPath="";
  	    var uploadFileName="userPics/"+userId;
  	    $.ajaxFileUpload
  	    (
  	        {
  	        	async:false,
  	            url:contextPath+'/cps/userPics/ajaxUpload?imagePath='+uploadFileName+"&adapt=true",
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
  	                    }
  	                    window.location.reload();
  	                }
  	            },
  	            error: function (result, status, e)
  	            {
  	                alert(e);
  	            }
  	        }
  	    )
  	}
      
    function removeFile(item){
    	$(item).unbind("click");
    	if($("input[name='pics']").is(":hidden")){
    		$("input[name='pics']").show();
    	}else{
    		ajaxRemove();
    	}
    }
    
    function ajaxRemove(){
    	var checkedVal=$("input[name='pics']:checked");
    	if(checkedVal == null || checkedVal.length == 0){
    		$("input[name='pics']").hide();
    	}else{
    		var picLinks=[];
    		var index = 0;
    		$(checkedVal).each(function(){
    			picLinks[index++]=$(this).val();
    		})
    		var picLinksjson = JSON.stringify(picLinks);
        	$.ajax({
    	 		   async:true,
    	 		   data:{pics:picLinksjson},
    	 		   dataType:"json",
    	 		   url:contextPath+"/cps/userPics/remove",
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
    	    })
    	}
    }
    
    $("#editName").click(function(){
        var index = 0;
        $("#editName").unbind("click");
    	$("span[dataId='editName']").each(function(){
    		$(this).parent().hide();
        	var name = $(this).html();
        	$(this).parent().before("<input type='text' value='"+name+"'>");
    	});
    	
    	$("#editName").click(function(){
    		var picInfoArray = [];
    		var index = 0;
    		$("span[dataId='editName']").each(function(){
    			var picName = $(this).parent().parent().children("input:eq(0)").val();
    			var picUrl = $(this).attr('dataVal');
    			var picInfo = {name:picName,url:picUrl};
    			picInfoArray[index++] = picInfo;
    		});
    		ajaxUpdatePicName(JSON.stringify(picInfoArray));
//    		alert(JSON.stringify(picInfoArray));
        });
    })
    
    function ajaxUpdatePicName(jsonVal){
    	$.ajax({
	 		   async:false,
	 		   data:{picsJson:jsonVal},
	 		   dataType:"json",
	 		   url:contextPath+"/cps/userPics/updatePicName",
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
	    })
    }
    </script>
</body>
</html>
