<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li>
                <div class="user-img-div">
                    <img src="${contextPath}/cps/img/user.png" class="img-thumbnail" />

                    <div class="inner-text">文甲东<br/><small>最后一次登录 : 2 Weeks Ago </small><br/>自知之明，兢兢业业，顺其自然。</div>
                </div>
            </li>
            <li>
                <a <#if page_name='wdoc'>class="active-menu"</#if> href="${contextPath}/cps/dft/wdoc"><i class="fa fa-desktop"></i>写文章</a>
            </li>
            <li>
                <a <#if page_name='docms'>class="active-menu"</#if> href="${contextPath}/cps/pding/docms"><i class="fa fa-dashboard "></i>文章管理</a>
            </li>
             <li>
                <a href="${contextPath}/cps/dft/draft"><i class="fa fa-yelp "></i>流量统计</a>
            </li>
            <li>
                <a <#if page_name='source'>class="active-menu"</#if> href="${contextPath}/cps/source"><i class="fa fa-flash "></i>素材管理</a>
                
            </li>
            <li>
                <a <#if page_name='psoninf'>class="active-menu"</#if> href="${contextPath}/cps/user/psoninf"><i class="fa fa-anchor "></i>个人信息</a>
            </li>
            <li>
                <a <#if page_name='draft'>class="active-menu"</#if> href="${contextPath}/cps/dft/draft"><i class="fa fa-yelp "></i>草稿箱</a>
            </li>
            <li>
                <a <#if page_name='feedback'>class="active-menu"</#if> href="${contextPath}/cps/fb/feedback"><i class="fa fa-square-o "></i>反馈</a>
            </li>
        </ul>
    </div>
</nav>
<#include "/cps/common/alert.ftl">