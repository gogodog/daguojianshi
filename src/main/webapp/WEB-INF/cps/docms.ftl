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
		                               审核时间：${object.audit_time?string("yyyy-MM-dd HH:mm:ss")}<br>
		                               发布时间：${object.publish_time?string("yyyy-MM-dd HH:mm:ss")}<br>
		                            </td>
		                            <td>
		                               </i><a href="${contextPath}/cps/previewPending?aid=${object.id}">查看</a>         
		                               </i>提审
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
</body>
</html>
