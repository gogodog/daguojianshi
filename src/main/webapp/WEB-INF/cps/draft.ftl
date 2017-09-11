<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
    	<#include "/cps/common/top.ftl">
        <#assign page_name='draft'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">草稿箱</h1>
                        <h1 class="page-subhead-line">[温馨提示]提审之后请到文章管理查看已提审的文章，审核时间一般为1-3个工作日请耐心等候. </h1>
                    </div>
                </div>
                <!-- page begin-->
                <div class="row">
         			<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
		                    <thead>
		                        <tr>
		                            <th>类型</th>
		                            <th>作者</th>
		                            <th>标题</th>
		                            <th>最后一次保存时间</th>
		                            <th>操作</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <#list pageinfo.objects as object>
		                        <tr>
		                            <td>${object.type.value}</td>
		                            <td>${object.author}</td>
		                            <td>${object.title}</td>
		                            <td>${object.update_time?string("yyyy-MM-dd HH:mm:ss")}</td>
		                            <td>
		                              <a href="${contextPath}/cps/wdoc?aid=${object.id}">继续编辑</a>&nbsp;&nbsp;
		                              <a href="${contextPath}/cps/previewDraft?aid=${object.id}">预览</a>&nbsp;&nbsp;
		                              <a href="javascript:void(0)" onclick="deleteA('${object.id}');">删除</a>
		                              <a href="javascript:void(0)" onclick="submitAudit('${object.id}');">提审</a>
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
    <script>
    var contextPath="${contextPath}";
    function deleteA(aid){
    	var txt = "您确定要删除这条数据吗？";
    	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
    		window.location.href=contextPath+"/cps/dltdft?aid="+aid;
    	}})
    }
    function submitAudit(aid){
    	$.ajax({
 		   async:false,
 		   data:{aid:aid},
 		   dataType: "json",
 		   url:contextPath+"/cps/submitAudit",
 		   type:"POST",
 		   success:function(data) {
                if(data.error){
             	    alert(data.errorMessage);
                }else{
                	alert('提审成功');
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
