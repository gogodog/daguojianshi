<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li>
                <div class="user-img-div">
                    <img src="${user.headimgurl}" class="img-thumbnail" />

                    <div class="inner-text">${user.username}<br/><small>最后一次登录 : 2 Weeks Ago </small><br/>自知之明，兢兢业业，顺其自然。</div>
                </div>
            </li>
            <li>
                <a <#if page_name='dft/wdoc'>class="active-menu"</#if> href="/cps/dft/wdoc"><i class="fa fa-desktop"></i>写文章</a>
            </li>
            <li>
                <a <#if page_name='pding/docms'>class="active-menu"</#if> href="/cps/pding/docms"><i class="fa fa-dashboard "></i>文章管理</a>
            </li>
             <li>
                <a <#if page_name='flwststcs/list'>class="active-menu"</#if> href="/cps/flwststcs/list"><i class="fa fa-yelp "></i>流量统计</a>
            </li>
            <li>
                <a <#if page_name='userPics/source'>class="active-menu"</#if> href="/cps/userPics/list"><i class="fa fa-flash "></i>素材管理</a>
            </li>
            <li>
                <a <#if page_name='user/psoninf'>class="active-menu"</#if> href="/cps/user/psoninf"><i class="fa fa-anchor "></i>个人信息</a>
            </li>
            <li>
                <a <#if page_name='dft/draft'>class="active-menu"</#if> href="/cps/dft/draft"><i class="fa fa-yelp "></i>草稿箱</a>
            </li>
            <li>
                <a <#if page_name='fb/feedback'>class="active-menu"</#if> href="/cps/fb/feedback"><i class="fa fa-square-o "></i>反馈</a>
            </li>
        </ul>
    </div>
</nav>
<#include "/cps/common/alert.ftl">