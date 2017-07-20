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
		                            <th>名称</th>
		                            <th>状态</th>
		                            <th>类型</th>
		                            <th>类别</th>
		                            <th>创建时间</th>
		                            <th>操作</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    <#list list as i>
		                        <tr>
		                            <td>${i_index+1}</td>
		                            <td>${i.name}</td>
		                            <td>${i.status}</td>
		                            <td>${i.srctype}</td>
		                            <td>${i.category}</td>
		                            <td>提审：${i.ts?string("yyyy-MM-dd HH:mm:ss")}<br>审核：${i.sh?string("yyyy-MM-dd HH:mm:ss")}</td>
		                            <td></i>查看          </i>提审</td>
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
