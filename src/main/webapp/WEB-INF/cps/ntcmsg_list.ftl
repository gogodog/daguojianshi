<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
    	<#include "/cps/common/top.ftl">
    	<#if condtion.status == 'UNREAD'>
           <#assign page_name='ntcmsg/unreadList'>
        <#else>
    	   <#assign page_name='ntcmsg/readList'>
        </#if>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">通知消息</h1>
                    </div>
                </div>
                <!-- page begin-->
                <div class="row">
         			<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
		                    <thead>
		                        <tr>
		                            <th style="width:18%">时间</th>
		                            <th style="width:82%">消息内容</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <#list pageinfo.objects as object>
		                        <tr <#if object.status == "UNREAD">class="warning"</#if> dataId="${object.id}">
		                            <td>${object.create_time?string("yyyy-MM-dd HH:mm:ss")}</td>
		                            <td>${object.message}</td>
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
    <script>
    var contextPath="${contextPath}";
    var status = "${condition.status}";
    if(status == 'UNREAD'){
    	readMessage();
    }
//    document.onreadystatechange = function () {   
//        if(document.readyState=="complete") {          
//        	readMessage();
//         }   
//     }   
    
    function readMessage(){
    	var index=0;
    	var unreadIds=[];
    	$("tr[class='warning']").each(function(){
    		unreadIds[index++] = $(this).attr("dataId");
    	})
    	if(index > 0){
    		$.ajax({
    	  		   async:false,
    	  		   data:{ids:JSON.stringify(unreadIds)},
    	  		   dataType: "json",
    	  		   url:contextPath+"/cps/ntcmsg/readMessage",
    	  		   type:"POST",
    	  		   success:function(data) {
    	                 if(data.error){
    	                	 console.log("读消息成功");
    	                 }
    	           }, 
    	           error:function(){
    	                 console.log("服务器繁忙...");
    	           }
    	     })
    	}
    }
    </script>
</body>
</html>
