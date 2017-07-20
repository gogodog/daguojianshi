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
                        <h1 class="page-subhead-line">[温馨提示]提审时间一般为1-3个工作日请耐心等候. </h1>
                    </div>
                </div>
                <!-- page begin-->
                <div class="row">
         			<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
		                    <thead>
		                        <tr>
		                            <th>序号</th>
		                            <th>名称</th>
		                            <th>最后一次保存时间</th>
		                            <th>操作</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <tr>
		                            <td>1</td>
		                            <td>IS在中东大势已去 库尔德人或成新“以色列”</td>
		                            <td>2017:01:01 18:00:00</td>
		                            <td></i>继续编辑</td>
		                        </tr>
		                        <#list list as i>
		                        <tr>
		                            <td>${i_index+1}</td>
		                            <td>${i.name}</td>
		                            <td>${i.last?string("yyyy-MM-dd HH:mm:ss")}</td>
		                            <td></i>继续编辑</td>
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
