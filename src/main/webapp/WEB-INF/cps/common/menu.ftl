<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li>
                <div class="user-img-div">
                    <a href ="/cps"><img src="${user.headimgurl}" class="img-thumbnail" /></a>

                    <div class="inner-text">${user.username}<br/><small>最后一次登录 : 2 Weeks Ago </small><br/>自知之明，兢兢业业，顺其自然。</div>
                </div>
            </li>
            <#list cpsmenus as menu>
               <li>
                    <a ${menu.pageName?seq_contains(page_name)?string("class=\"active-menu\"", "")} href="${menu.url}">
                        <i class="${menu.css}"></i>${menu.name}
                    </a>
               </li>
            </#list>
        </ul>
    </div>
</nav>
<#include "/cps/common/alert.ftl">