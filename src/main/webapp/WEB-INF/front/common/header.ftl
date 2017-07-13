<header class="header">
  <nav class="navbar navbar-default" id="navbar">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header-navbar" aria-expanded="false"> <span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        <h1 class="logo hvr-bounce-in"><a class="titlelog" href="${contextPath}/timeline"><img src="${contextPath}/front/images/slogo.png">大国简史</a></h1>
      </div>
      <div class="collapse navbar-collapse" id="header-navbar">
        <form class="navbar-form visible-xs" action="/Search" method="post">
          <div class="input-group">
            <input type="text" name="keyword" class="form-control" placeholder="请输入关键字" maxlength="20" autocomplete="off">
            <span class="input-group-btn">
            <button class="btn btn-default btn-search" name="search" type="button" onclick="searchByKeyword('${page_name}');">搜索</button>
            </span>
          </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
          <li><a data-cont="大国简史" title="大国简史" href="${contextPath}/index">首页</a></li>
          <#list types as type>
            <li><a data-cont="大国简史" title="大国简史" href="${contextPath}/index?type=${type}">${type.value}</a></li>
          </#list>
        </ul>
      </div>
    </div>
  </nav>
</header>