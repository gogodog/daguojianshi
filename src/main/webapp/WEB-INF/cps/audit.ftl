<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
        <#include "/cps/common/top.ftl">
        <#assign page_name='dft/audit'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <input type="hidden" id="aid" value="${aid}">
                    <div class="col-md-12">
                        <h1 class="page-head-line">选择一张封面图</h1>
                           <select class="form-control" style="width:20%" id="pics" onchange="changeImg(this.value);">
                        </select>
                    </div>
                    <div class="col-md-2" style="display:none">
                       <br><img src="" id="choosePic" style="width:200px;height:200px;">
                    </div>
                    <div class="col-md-12" style="display:none">
                       <br><button class="btn btn-primary" onclick="doSubmit();">提审</button>
                    </div>
                </div>
            </div>
        </div>
   <#include "/cps/common/f-static.ftl">
   <script>
      window.onload=function(){
    	  getPics();
	  }
     
      function getPics(){
    	  $.ajax({
    		   async:false,
    		   data:{},
    		   dataType: "json",
    		   url:"/cps/userPics/ajaxList",
    		   type:"POST",
    		   success:function(data) {
                   if(data.error){
                	    console.log(data.errorMessage);
                   }else{
                	   if(data!=null && data.length>0){
                		   var picsHtml='';
                		   for(var i=0;i<data.length;i++){
                			   picsHtml+="<option value=\""+data[i].address+"\">"+data[i].name+"</option>"
                		   }
                		   $("#choosePic").attr('src',data[0].address)
                		   $("#choosePic").parent().show();
                		   $("#choosePic").parent().next().show();
                		   $("#pics").html(picsHtml);
                	   }
                   }
               }, 
               error:function(){
                   console.log("服务器繁忙...");
               }
           })
      }
      
      function changeImg(url){
    	  $("#choosePic").attr('src',url);
      } 
      
      function doSubmit(){
    	  var picUrl = $("#pics").val();
    	  var aid = $("#aid").val();
    	  $.ajax({
   		   async:false,
   		   data:{aid:aid,showPic:picUrl},
   		   dataType: "json",
   		   url:"/cps/dft/submitAudit",
   		   type:"POST",
   		   success:function(data) {
                  if(data.error){
               	    console.log(data.errorMessage);
                  }else{
                	location.href = "/cps/pding/docms";
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
