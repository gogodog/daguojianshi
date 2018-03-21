<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
        <#include "/cps/common/top.ftl">
        <#assign page_name='user/psoninf'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">个人信息</h1>
                        <h1 class="page-subhead-line">完善的个人资料既可以更完美的展示您的魅力！&nbsp;&nbsp;&nbsp;<button type="button" id="joinGroup" class="btn btn-info">加入群组</button></h1>
                    </div>
	            <div class="col-md-6">
                        <div class="panel panel-info">
                            <div class="panel-heading"><i class="fa fa-bell fa-fw"></i>基本信息</div>
                            <div class="panel-body">
                                <div class="list-group">
									<a href="javascript:void(0)" class="list-group-item">姓名<span class="pull-right text-muted small"><em>${adminUserInfo.real_name}</em></span></a>
									<a href="javascript:void(0)" class="list-group-item">性别<span class="pull-right text-muted small"><em><#if adminUserInfo.sex == 1>男<#elseif adminUserInfo.sex == 2>女<#else></#if></em></span></a>
									<a href="javascript:void(0)" class="list-group-item">年龄<span class="pull-right text-muted small"><em>${adminUserInfo.age}</em></span></a>
									<a href="javascript:void(0)" class="list-group-item">组织<span class="pull-right text-muted small"><em>${adminUserInfo.organization}</em></span></a>
								</div>
                                <a href="javascript:void(0)" class="btn btn-info btn-block" id="editBaseInfo">编辑</a>
                            </div>
                        </div>
                    </div>
            	</div>
            	<#if adminUserInfo.real_name??> 
            	<div class="row">
            	<div class="col-md-6">
                        <div class="panel panel-success">
                            <div class="panel-heading"><i class="fa fa-envelope fa-fw"></i>联系方式</div>
                            <div class="panel-body">
                                <div class="list-group">
									<a href="javascript:void(0)" class="list-group-item">电子邮件<span class="pull-right text-muted small"><em>${adminUserInfo.email}</em></span></a>
									<a href="javascript:void(0)" class="list-group-item">电话<span class="pull-right text-muted small"><em>${adminUserInfo.mobile}</em></span></a>
									<a href="javascript:void(0)" class="list-group-item">常住地址<span class="pull-right text-muted small"><em>${adminUserInfo.address}</em></span></a>
                                </div>
                                <a href="javascript:void(0)" class="btn btn-info btn-block" id="editConnectInfo">编辑</a>
                            </div>
                        </div>
                    </div>
            	</div>
            	</#if>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
   <#include "/cps/common/f-static.ftl">
   <link rel="stylesheet" type="text/css" href="/cps/css/alertify.css?v=${staticVersion}">
   <script src="/cps/js/alertify.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
   <script>
   var contextPath="${contextPath}";
     $("#editBaseInfo").click(function(){
    	  $(this).html("保存");
    	  $(this).unbind("click");
    	  var div=$(this).prev();
    	  var array = new Array();
    	  array[0]="姓名";
    	  array[1]="性别";
    	  array[2]="年龄";
    	  array[3]="学校";
    	  var index = 0;
    	  $(div).children("a").each(function(){
    		  var prefix=array[index];
    		  var em= $(this).children("span").children("em").html();
    		  if(index == 1){
    			  var select = "";
    			  if(em == '女'){
    				  select = "<select name=\""+prefix+"\"><option value=\"1\">男</option><option value=\"2\" selected>女</option></select>";
    			  }else{
    				  select = "<select name=\""+prefix+"\"><option value=\"1\" selected>男</option><option value=\"2\">女</option></select>";
    			  }
    			  $(this).html(prefix+"<span class=\"pull-right text-muted small\"><em>"+select+"</em></span>");
    		  }else{
    			  var input = "<input type=\"text\" name=\""+prefix+"\" value=\""+em+"\">";
    			  $(this).html(prefix+"<span class=\"pull-right text-muted small\"><em>"+input+"</em></span>");
    		  }
    		  index++;
    	  });
    	  $(this).bind("click",saveBaseInfo);
     })
      
      function saveBaseInfo(item){
    	 var sex=$("select[name='性别']").val();
    	 var real_name=$("input[name='姓名']").val();
    	 var age=$("input[name='年龄']").val();
    	 var organization=$("input[name='学校']").val();
    	 if(!(sex==1||sex==2)){
    		 alert("请选择性别");
    		 return;
    	 }
    	 if(real_name==null||real_name.length<2||real_name.length>10){
    		 alert("请写正确的姓名");
    		 return;
    	 }
    	 if(organization==null||organization.length<2||organization.length>30){
    		 alert("请写正确的组织");
    		 return;
    	 }
    	 if(age!=parseInt(age)||age<1||age>100){
    		 alert("请写正确的年龄");
    		 return;
    	 }
    	 $.ajax({
   		   async:false,
   		   data:{sex:sex,real_name:real_name,age:age,organization:organization,source:1},
   		   dataType: "json",
   		   url:contextPath+"/cps/user/editUserInfo",
   		   type:"POST",
   		   success:function(data) {
                  if(data.error){
               	    alert(data.errorMessage);
                  }else{
                	  if(data.objects!=null && data.objects!='' && data.objects.isNeedRedirect!=null && data.objects.isNeedRedirect){
                  		window.location.href = '/cps/login'
                  	  }else{
                  	  	window.location.reload();
                  	  }
                  }
              }, 
              error:function(){
                  console.log("服务器繁忙...");
              }
          })
      }
     
     $("#editConnectInfo").click(function(){
         $(this).html("保存");
   	     $(this).unbind("click");
   	     var div=$(this).prev();
   	     var array = new Array();
   	     array[0]="电子邮件";
   	     array[1]="电话";
   	     array[2]="常住地址";
   	     var index=0;
   	     $(div).children("a").each(function(){
   	   	   var prefix=array[index];
   	   	   var em= $(this).children("span").children("em").html();
   	   	   var input = "<input type=\"text\" name=\""+prefix+"\" value=\""+em+"\">";
   	   	   $(this).html(prefix+"<span class=\"pull-right text-muted small\"><em>"+input+"</em></span>");
   	   	   index++;
   	     });
   	     $(this).bind("click",saveConnectInfo);
     });
     
     function saveConnectInfo(){
    	 var email=$("input[name='电子邮件']").val();
    	 var mobile=$("input[name='电话']").val();
    	 var address=$("input[name='常住地址']").val();
    	 if(email==null||email.length<5){
    		 alert("请写正确的邮箱");
    		 return;
    	 }
    	 if(mobile==null||mobile.length<5){
    		 alert("请写正确的电话");
    		 return;
    	 }
    	 if(address==null||address.length<3){
    		 alert("请写正确的常住地址");
    		 return;
    	 }
    	 $.ajax({
     		   async:false,
     		   data:{email:email,mobile:mobile,address:address,source:2},
     		   dataType: "json",
     		   url:contextPath+"/cps/user/editUserInfo",
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
     
     $("#joinGroup").click(function(){
    	 var message = "推荐码：<input type=\"text\" id=\"invitation_code\">";  
    	 alertify.confirm(message, function () {
    		   var code = $("#invitation_code").val();
   		   $.ajax({
   	 		   async:false,
   	 		   data:{code:code},
   	 		   dataType: "json",
   	 		   url:contextPath+"/cps/user/joinGroup",
   	 		   type:"POST",
   	 		   success:function(data) {
   	                if(data.error){
   	                	alertify.error(data.errorMessage);
   	                }else{
   	                	alert('加入成功');
   	                    window.location.reload();
   	                }
   	            }, 
   	            error:function(){
   	            	alertify.error("服务器繁忙...");
   	            }
   	        })
   	   }, function() {
//   		   alertify.error("cancel");
   	       // user clicked "cancel"
   	   }); 
     })
   </script>
</body>
</html>
