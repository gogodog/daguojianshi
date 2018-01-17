<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
    	<#include "/cps/common/top.ftl">
        <#assign page_name='pding/docms'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">文章管理</h1>
                        <h1 class="page-subhead-line">[温馨提示]提审时间一般为1-3个工作日请耐心等候. </h1>
                        <form action="${contextPath}/cps/pding/docms" method="post" id="form1">
                           <label>分类:</label>
                           <div class="btn-group">
		                        <button style="width:100px;" data-toggle="dropdown" class="btn btn-warning dropdown-toggle"><span id="type_show" >${condition.type.value!'全部'}</span><span class="caret"></span></button>
		                        <ul class="dropdown-menu">
		                        	<li><a href="#" tosl="" onmouseup="changeSelect(this,$('#type'),$('#type_show'))">全部</a></li>
		                            <#list typeList as type>
		                            	<li><a href="#" tosl="${type}" onmouseup="changeSelect(this,$('#type'),$('#type_show'))">${type.value}</a></li>
		                            </#list>
		                        </ul>
		                    </div>
    			           <label>状态:</label>
                           <div class="btn-group">
		                        <button style="width:100px;" data-toggle="dropdown" class="btn btn-warning dropdown-toggle"><span id="status_show" >${condition.status.value!'全部'}</span><span class="caret"></span></button>
		                        <ul class="dropdown-menu">
		                        	<li><a href="#" tosl="" onmouseup="changeSelect(this,$('#status'),$('#status_show'))">全部</a></li>
		                            <#list statusList as status>
		                            	<li><a href="#" tosl="${status}" onmouseup="changeSelect(this,$('#status'),$('#status_show'))">${status.value}</a></li>
		                            </#list>
		                        </ul>
		                    </div>
		                    <input type="hidden" value="${condition.status}" id="status" name="status"/>
		                    <input type="hidden" value="${condition.type}" id="type" name="type"/>
                            <label>标题:<input class="form-control" style="display: inline;width: auto;" placeholder="输入标题" type="text" name="title" value="${condition.title}"></label>
                            <label><button class="btn btn-primary" name="conditionButton"><i class="glyphicon glyphicon-search"></i>查询</button></label>
                        </form>
                        <h1 class="page-head-line"></h1>
    			    </div>
         			<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
		                    <thead>
		                        <tr>
		                            <th style="width:30%">标题</th>
		                            <th style="width:10%">状态</th>
		                            <th style="width:10%">类型</th>
		                            <th style="width:10%">作者</th>
		                            <th style="width:25%">时间</th>
		                            <th style="width:15%">操作</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    <#list pageinfo.objects as object>
		                        <tr>
		                            <td>${object.title}</td>
		                            <td>${object.status.value}</td>
		                            <td>${object.type.value}</td>
		                            <td>${object.author}</td>
		                            <td>
		                               提审时间：${object.create_time?string("yyyy-MM-dd HH:mm:ss")}<br>
		                               <#if object.audit_time??>审核时间：${object.audit_time?string("yyyy-MM-dd HH:mm:ss")}<br></#if>
		                               <#if object.publish_time??>发布时间：${object.publish_time?string("yyyy-MM-dd HH:mm:ss")}<br></#if>
		                            </td>
		                            <td>
		                               <button type="button" class="btn btn-xs btn-primary" onclick="location.href='${contextPath}/cps/pding/previewPending?aid=${object.id}'">查看</button>
		                               <#if object.status == 'Audit_FAIL'>
		                                 <button type="button" class="btn btn-xs btn-primary" onclick="showAuditFailDesc('${rejectMap[object.id]}');">拒绝原因</button>
		                                <!-- </i><a href="javascript:void(0)" onclick="showAuditFailDesc('${object.audit_desc}');">拒绝原因</a>  -->
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
    <script src="/admin/js/confirm/xcConfirm.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
    <script language="javascript" src="/admin/js/My97DatePicker/wdatePicker.js?v=${staticVersion}"></script>
    <script src="/cps/js/business/condition.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="/cps/css/alertify.css?v=${staticVersion}">
    <script src="/cps/js/alertify.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
    <script>
    	function changeSelect(that,hidName,showName){
    		hidName.val($(that).attr('tosl'));
    		showName.text($(that).text());
    	}
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
    	   var message = "<h4>审核不通过需要给出不通过的原因</h4><p style='float:left'>审核&nbsp;:&nbsp<select id='auditStatus' onchange='changeDescShow(this)'><option value='PUBLISH_PENDING'>通过</option><option value='Audit_FAIL'>不通过</option></select></p><br/>";  
           alertify.prompt(message, function (val, e) {  
               if(e) {  
            	   var status = $("#auditStatus").val();
        		   if(status == 'Audit_FAIL'){
        			    if(val==null||val.length<2){
        			    	alertify.error("审核不通过需要给出不通过的原因");  
            				return;
        			    }
        		   }
        		   $.ajax({
        	 		   async:false,
        	 		   data:{aid:aid,status:status,audit_desc:val},
        	 		   dataType: "json",
        	 		   url:contextPath+"/cps/audit",
        	 		   type:"POST",
        	 		   success:function(data) {
        	                if(data.error){
        	                	alertify.error(data.errorMessage);
        	                }else{
        	                	alert("操作成功");      
        	                	location.reload();
        	                }
        	            }, 
        	            error:function(){
        	                alertify.error("服务器繁忙...");  
        	            }
        	        })
               } else {  
                   alertify.error("审核不通过需要给出不通过的原因");  
               }  
           }, "Enter a value");  
    	   
//    	   var txt = "审核:<select id='auditStatus' onchange='changeDescShow(this)'><option value='PUBLISH_PENDING'>通过</option><option value='Audit_FAIL'>不通过</option></select>";
//    	   txt+="<span style='display:none;' id='audit_desc'><br>描述：<input maxlength='200' style='width:200px;' name='audit_desc'/></div>"
//    	   window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
//    		   var status = $("#auditStatus").val();
//    		   var audit_desc='';
//    		   if(status == 'Audit_FAIL'){
//    			   audit_desc = $("input[name='audit_desc']").val();
//    			   if(audit_desc == null || audit_desc.length < 1){
//    				   alert('不通过需要给一定的理由');
//    				   return;
//    			   }
//    		   }
//    		   $.ajax({
//    	 		   async:false,
//    	 		   data:{aid:aid,status:status,audit_desc:audit_desc},
//    	 		   dataType: "json",
//    	 		   url:contextPath+"/cps/audit",
//    	 		   type:"POST",
//    	 		   success:function(data) {
//    	                if(data.error){
//    	             	    alert(data.errorMessage);
//    	                }else{
//    	                	alert('操作成功');
//    	                	location.reload();
//    	                }
//    	            }, 
//    	            error:function(){
//    	                console.log("服务器繁忙...");
//    	            }
//    	        })
//           }})
       }
       
       function showPublish(aid){
    	   var txt='展示时间&nbsp;:&nbsp;<input type="text" name="show_time" onClick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'});" class="Wdate" style="width:200px"><br>';
    	   txt+='访问量基数&nbsp;:&nbsp;<input type="text" name="visits" style="width:200px;height:20px"><br>'
    	   alertify.confirm(txt, function () {
    		   var show_time = $("input[name='show_time']").val();
    		   var visits = $("input[name='visits']").val();
    		   $.ajax({
    	 		   async:false,
    	 		   data:{aid:aid,show_time:show_time,visits:visits},
    	 		   dataType: "json",
    	 		   url:contextPath+"/cps/publish",
    	 		   type:"POST",
    	 		   success:function(data) {
    	                if(data.error){
    	                	alertify.error(data.errorMessage);
    	                }else{
    	                	alert('操作成功');
    	                	location.reload();
    	                }
    	            }, 
    	            error:function(){
    	            	alertify.error("服务器繁忙...");
    	            }
    	        })
    	   }, function() {
//    		   alertify.error("cancel");
    	       // user clicked "cancel"
    	   });
//    	   $.ajax({
//	 		   async:false,
//	 		   data:{aid:aid},
//	 		   dataType: "json",
//	 		   url:contextPath+"/cps/publish",
//	 		   type:"POST",
//	 		   success:function(data) {
//	                if(data.error){
//	             	    alert(data.errorMessage);
//	                }else{
//	                	alert('操作成功');
//	                	location.reload();
//	                }
//	            }, 
//	            error:function(){
//	                console.log("服务器繁忙...");
//	            }
//	        })
       }
       
       function showAuditFailDesc(audit_desc){
    	   
//    	   var txt = audit_desc;
//    	   window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
//           }})
    	   
    	   var message = "<h3>拒绝原因</h3><p><strong>"+audit_desc+"</strong></p><br/>";  
           alertify.alert(message);  
       }
       
    </script>
</body>
</html>
