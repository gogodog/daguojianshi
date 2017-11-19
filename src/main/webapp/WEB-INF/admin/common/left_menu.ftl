<div class="public-ifame-leftnav">
			<div class="public-title-warrp">
				<div class="public-ifame-title ">
					<a href="javascript:void(0)">导航</a>
				</div>
			</div>
			<ul class="left-nav-list">
			
			<#list menus as menu>
			   <li class="public-ifame-item">
		         <a href="javascript:;">${menu.name}</a>
		         <div class="ifame-item-sub">
			         <ul>
			            <#list menu.children as child>
			               <li><a href="${contextPath}${child.url}" target="content">${child.desc}</a></li>
			            </#list>
			         </ul>
		         </div>
	          </li>
			</#list>
			
			<!-- 
				<li class="public-ifame-item">
					<a href="javascript:;">内容管理</a>
					<div class="ifame-item-sub">
						<ul>
						    <li class="active"><a href="${contextPath}/admin/atcp/articlescrapList" target="content">文章管理</a></li>
							<li><a href="${contextPath}/admin/cul/carouselList" target="content">轮播图管理</a></li>
							<li><a href="${contextPath}/admin/rcma/recommedArticlescrapList" target="content">推荐管理</a></li>
							<li><a href="${contextPath}/admin/channel/list" target="content">频道管理</a></li>
							<li><a href="${contextPath}/admin/idxcfg/list" target="content">首页配置管理</a></li>
							<li><a href="${contextPath}/admin/pding/docms" target="content">文章审核管理</a></li>
						</ul>
					</div>
				</li>
				<li class="public-ifame-item">
				    <a href="javascript:;">广告管理</a>
				    <div class="ifame-item-sub">
					    <ul>
					        <li class="active"><a href="${contextPath}/admin/ad/adList" target="content">广告内容管理</a></li>
					    </ul>
				    </div>
			    </li>
			    <li class="public-ifame-item">
			        <a href="javascript:;">反馈管理</a>
			        <div class="ifame-item-sub">
				        <ul>
				             <li class="active"><a href="${contextPath}/admin/fb/feedBackList" target="content">反馈管理</a></li>
				        </ul>
			        </div>
		        </li>
		        <li class="public-ifame-item">
		            <a href="javascript:;">后台用户管理</a>
		            <div class="ifame-item-sub">
			            <ul>
			                 <li class="active"><a href="${contextPath}/admin/admin/athrtyList" target="content">权限管理</a></li>
			                 <li><a href="${contextPath}/admin/admin/roleList" target="content">角色管理</a></li>
			                 <li><a href="${contextPath}/admin/adnur/adminList" target="content">用户管理</a></li>
			                 <li><a href="${contextPath}/admin/oprtlg/list" target="content">操作日志</a></li>
			            </ul>   
		            </div>
	            </li>   -->
			</ul>
		</div>