<nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.html">Simple History<br> 简史</a>
    </div>
    <div class="header-right">
        <a href="${contextPath}/cps/ntcmsg/unreadList" class="btn btn-info" title="New Message"><b><#if unreadCount?? && unreadCount gt 0 >${unreadCount}</#if> </b><i class="fa fa-envelope-o fa-2x"></i></a>
        <a href="${contextPath}/cps/ntcmsg/readList" class="btn btn-primary" title="New Task"><i class="fa fa-bars fa-2x"></i></a>
        <a href="${contextPath}/cps/login" class="btn btn-danger" title="Logout"><i class="fa fa-exclamation-circle fa-2x"></i></a>
    </div>
</nav>