<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
    	<#include "/cps/common/top.ftl">
        <#assign page_name='docms'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">文章管理</h1>
                        <h1 class="page-subhead-line">[温馨提示]提审时间一般为1-3个工作日请耐心等候. </h1>
                    </div>
         			<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
		                    <thead>
		                        <tr>
		                            <th>序号</th>
		                            <th>标题</th>
		                            <th>状态</th>
		                            <th>类型</th>
		                            <th>作者</th>
		                            <th>时间</th>
		                            <th>操作</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    <#list pageinfo.objects as object>
		                        <tr>
		                            <td>${i_index+1}</td>
		                            <td>${object.title}</td>
		                            <td>${object.status.value}</td>
		                            <td>${object.type.value}</td>
		                            <td>${object.author}</td>
		                            <td>
		                               提审时间：${object.create_time?string("yyyy-MM-dd HH:mm:ss")}<br>
		                               审核时间：<#if object.audit_time??>${object.audit_time?string("yyyy-MM-dd HH:mm:ss")}</#if><br>
		                               发布时间：<#if object.publish_time??>${object.publish_time?string("yyyy-MM-dd HH:mm:ss")}</#if><br>
		                            </td>
		                            <td>
		                               </i><a href="${contextPath}/cps/previewPending?aid=${object.id}">查看</a>     
		                               <#if object.status == 'AUDIT_PENDING'>
		                                 </i><a href="javascript:void(0)" onclick="showAudit('${object.id}');">审核</a>  
		                               </#if>
		                               <#if object.status == 'PUBLISH_PENDING'>
		                                 </i><a href="javascript:void(0)" onclick="showPublish('${object.id}');">发布</a>  
		                               </#if>
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
    <script src="${contextPath}/admin/js/confirm/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
    <script language="javascript" src="${contextPath}/admin/js/My97DatePicker/wdatePicker.js"></script>
    <script>
       var contextPath="${contextPath}";
       function changeDescShow(item){
    	   var value = $(item).val();
           if(value == 'Audit_FAIL'){
        	   $("#audit_desc").show();
           }else if(value == 'PUBLISH_PENDING'){
        	   $("#audit_desc").hide();
           }
       }
       function showAudit(aid){
    	   var txt = "审核:<select id='auditStatus' onchange='changeDescShow(this)'><option value='PUBLISH_PENDING'>通过</option><option value='Audit_FAIL'>不通过</option></select>";
    	   txt+="<span style='display:none;' id='audit_desc'><br>描述：<input maxlength='200' style='width:200px;' name='audit_desc'/></div>"
    	   window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
    		   var status = $("#auditStatus").val();
    		   var audit_desc='';
    		   if(status == 'Audit_FAIL'){
    			   audit_desc = $("input[name='audit_desc']").val();
    			   if(audit_desc == null || audit_desc.length < 1){
    				   alert('不通过需要给一定的理由');
    				   return;
    			   }
    		   }
    		   $.ajax({
    	 		   async:false,
    	 		   data:{aid:aid,status:status,audit_desc:audit_desc},
    	 		   dataType: "json",
    	 		   url:contextPath+"/cps/audit",
    	 		   type:"POST",
    	 		   success:function(data) {
    	                if(data.error){
    	             	    alert(data.errorMessage);
    	                }else{
    	                	alert('操作成功');
    	                	location.reload();
    	                }
    	            }, 
    	            error:function(){
    	                console.log("服务器繁忙...");
    	            }
    	        })
           }})
       }
       
       function showPublish(aid){
    	   $.ajax({
	 		   async:false,
	 		   data:{aid:aid},
	 		   dataType: "json",
	 		   url:contextPath+"/cps/publish",
	 		   type:"POST",
	 		   success:function(data) {
	                if(data.error){
	             	    alert(data.errorMessage);
	                }else{
	                	alert('操作成功');
	                	location.reload();
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
