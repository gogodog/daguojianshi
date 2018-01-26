<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
        <#include "/cps/common/top.ftl">
        <#assign page_name=''>
        <#include "/cps/common/menu.ftl">
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">公告</h1>
                        <h1 class="page-subhead-line">我们会为您展示3个月的公告信息，请注意查看</h1>
                	</div>
			    </div>
			    <#list list as announcement>
			      <div class="panel panel-default">
                     <div class="panel-heading">
                       <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">${announcement.show_time?string("yyyy-MM-dd HH:mm:ss")}</a>
                       </h4>
                     </div>
                     <div id="collapseTwo" class="panel-collapse in" style="height: auto;">
                        <div class="panel-body">
                           ${announcement.message}
                        </div>
                     </div>
                  </div>
			    </#list>
		    </div>
	     </div>
    <#include "/cps/common/f-static.ftl">
    <#include "/cps/common/viewImage.ftl">
</body>
</html>
