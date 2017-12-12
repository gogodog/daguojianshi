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
			</ul>
		</div>