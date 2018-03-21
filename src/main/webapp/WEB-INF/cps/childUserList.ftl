<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
    	<#include "/cps/common/top.ftl">
        <#assign page_name='childUser/list'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">成员管理</h1>
                        <h1 class="page-subhead-line"></h1>
                    </div>
                </div>
                <!-- page begin-->
                <div class="row">
         			<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
		                    <thead>
		                        <tr>
		                            <th style="width:10%">头像</th>
		                            <th style="width:13%">用户名</th>
		                            <th style="width:10%">真实姓名</th>
		                            <th style="width:10%">性别</th>
		                            <th style="width:15%">电话</th>
		                            <th style="width:25%">时间</th>
		                            <th style="width:17%">角色</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <#list pageinfo.objects as object>
		                        <tr>
		                            <td><img src="${object.headimgurl!''}" style="width:70px;height:70px"></td>
		                            <td>${object.username}</td>
		                            <td>${object.real_name}</td>
		                            <td><#if object.sex==1>男<#elseif object.sex==2>女</#if></td>
		                            <td>${object.mobile}</td>
		                            <td>
		                               创建时间:${object.create_time?string("yyyy-MM-dd HH:mm:ss")}<br>
		                               修改时间:${object.update_time?string("yyyy-MM-dd HH:mm:ss")}
		                            </td>
		                            <td>${object.role_name}</td>
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
    <link rel="stylesheet" type="text/css" href="/cps/css/alertify.css?v=${staticVersion}">
    <script src="/cps/js/alertify.js?v=${staticVersion}" type="text/javascript" charset="utf-8"></script>
</body>
</html>
