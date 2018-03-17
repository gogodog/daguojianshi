<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
    	<#include "/cps/common/top.ftl">
        <#assign page_name='ivtatncd/list'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                       <h1 class="page-head-line">推荐码管理</h1>
                       <button type="button" class="btn btn-lg btn-success" id="generateCode">生成推荐码</button>
                       <h1 class="page-subhead-line"></h1>
                </div>
         			<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
		                    <thead>
		                        <tr>
		                            <th style="width:30%">推荐码</th>
		                            <th style="width:10%">状态</th>
		                            <th style="width:20%">有效期</th>
		                            <th style="width:25%">时间</th>
		                            <th style="width:15%">使用者</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    <#list pageinfo.objects as object>
		                        <tr>
		                            <td>${object.invitationCode.invitation_code}</td>
		                            <td>${object.invitationCode.status.value}</td>
		                            <td>${object.invitationCode.valid_time?string("yyyy-MM-dd HH:mm:ss")}</td>
		                            <td>创建时间:${object.invitationCode.create_time?string("yyyy-MM-dd HH:mm:ss")}<br>
		                                修改时间:${object.invitationCode.update_time?string("yyyy-MM-dd HH:mm:ss")}</td>
		                            <td>
		                                ${object.toUserName}
		                            </td>
		                        </tr>
							</#list>
		                    </tbody>
               	 		</table>
						</div>
             			<hr />
             			<#include "/cps/common/spage.ftl">
         		</div>
                <!-- page end-->
            </div>
        </div>
    </div>
    
    <#include "/cps/common/f-static.ftl">
    <script src="/admin/js/confirm/xcConfirm.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
    <script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js?v=${staticVersion}"></script>
    <script src="/cps/js/business/condition.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="/cps/css/alertify.css?v=${staticVersion}">
    <script src="/cps/js/alertify.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
    <script>
       var contextPath = '${contextPath}';
       $("#generateCode").click(function(){
    	   $.ajax({
   	 		   async:false,
   	 		   dataType: "json",
   	 		   url:contextPath+"/cps/ivtatncd/produce",
   	 		   type:"POST",
   	 		   success:function(data) {
   	                if(data.error){
   	                	alertify.error(data.errorMessage);
   	                }else{
   	                	pop(data.objects.invitation_code);
   	                }
   	            }, 
   	            error:function(){
   	            	alertify.error("服务器繁忙...");
   	            }
   	        })
       })
       
       
       function pop(message){
    	   alertify.confirm(message, function () {
    		   alertify.alert(message);
    		   window.location.reload();
   	       }, function() {
   		       window.location.reload();
   	       }); 
       }
    </script>
</body>
</html>
