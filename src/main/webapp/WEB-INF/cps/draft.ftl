<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
    	<#include "/cps/common/top.ftl">
        <#assign page_name='dft/draft'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">草稿箱</h1>
                        <h1 class="page-subhead-line">[温馨提示]提审之后请到文章管理查看已提审的文章，审核时间一般为1-3个工作日请耐心等候. </h1>
                        <form action="${contextPath}/cps/dft/draft" method="post" id="form1">
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
		                         <input type="hidden" value="${condition.type}" id="type" name="type"/>
		                         <input type="hidden" value="${condition.member}" id="member" name="member"/>
                              <label>标题:<input class="form-control" style="display: inline;width: auto;" placeholder="输入标题" type="text" name="title" value="${condition.title}"></label>
                              <label><button class="btn btn-primary" name="conditionButton"><i class="glyphicon glyphicon-search"></i>查询</button></label>
		                 </form>
		                 <h1 class="page-head-line"></h1>
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
		                            <td><span class="label label-info">${object.type.value}</span></td>
		                            <td>${object.author}</td>
		                            <td>${object.title}</td>
		                            <td>${object.update_time?string("yyyy-MM-dd HH:mm:ss")}</td>
		                            <td>
		                              <button type="button" class="btn btn-xs btn-info" onclick="location.href='${contextPath}/cps/dft/previewDraft?aid=${object.id}'">预览</button>
		                              <#if object.status == 'INIT'||object.status == 'Audit_FAIL'>
		                                <button type="button" class="btn btn-xs btn-primary" onclick="location.href='${contextPath}/cps/dft/wdoc?aid=${object.id}'">继续编辑</button>
		                                <button type="button" class="btn btn-xs btn-danger" onclick="deleteA('${object.id}');">删除</button>
		                                <!-- <button type="button" class="btn btn-xs btn-warning" onclick="submitAudit('${object.id}');">提审</button> -->
		                                <button type="button" class="btn btn-xs btn-warning" onclick="location.href='${contextPath}/cps/dft/audit?aid=${object.id}'">提审</button>
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
    <script>
    var contextPath="${contextPath}";
    function changeSelect(that,hidName,showName){
		hidName.val($(that).attr('tosl'));
		showName.text($(that).text());
	}
    function deleteA(aid){
    	var txt = "您确定要删除这条数据吗？";
    	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){
    		window.location.href=contextPath+"/cps/dft/dltdft?aid="+aid;
    	}})
    }
    function submitAudit(aid){
    	$.ajax({
 		   async:false,
 		   data:{aid:aid},
 		   dataType: "json",
 		   url:contextPath+"/cps/dft/submitAudit",
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
