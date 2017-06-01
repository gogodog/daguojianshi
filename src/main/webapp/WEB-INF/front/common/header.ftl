<header class="header">
  <nav class="navbar navbar-default" id="navbar">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header-navbar" aria-expanded="false"> <span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        <h1 class="logo hvr-bounce-in"><a href="${contextPath}/index">大国简史</a></h1>
      </div>
      <div class="collapse navbar-collapse" id="header-navbar">
        <form class="navbar-form visible-xs" action="/Search" method="post">
          <div class="input-group">
            <input type="text" name="keyword" class="form-control" placeholder="请输入关键字" maxlength="20" autocomplete="off">
            <span class="input-group-btn">
            <button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
            </span>
          </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
          <li><a data-cont="大国简史" title="大国简史-中国正史" href="${contextPath}/index?type=HISTORY">大国简史之中国正史</a></li>
          <li><a data-cont="大国简史" title="大国简史-国外历史" href="${contextPath}/index?type=FOREIGN_HISTORY">大国简史之国外历史</a></li>
          <li><a data-cont="大国简史" title="大国简史-人文" href="${contextPath}/index?type=HUMANITY_HISTORY">大国简史之人文</a></li>
          <li><a data-cont="大国简史" title="大国简史-地理" href="${contextPath}/index?type=GEOGRAPHY_HISTORY">大国简史之地理</a></li>
          <li><a data-cont="大国简史" title="大国简史-野史" href="${contextPath}/index?type=UNOFFICIAL_HISTORY">大国简史之野史</a></li>
        </ul>
      </div>
    </div>
  </nav>
</header>