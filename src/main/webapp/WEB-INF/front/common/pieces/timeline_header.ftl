<header class="header">
  <nav class="navbar navbar-default" id="navbar">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" onclick="menu()"> <span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        <h1 class="logo hvr-bounce-in"><a class="titlelog" href="${contextPath}/timeline"><img src="${contextPath}/front/images/slogo.png">大国简史</a></h1>
      </div>
      <div class="collapse navbar-collapse" id="header-navbar">
        <form class="navbar-form visible-xs" action="/Search" method="post" style="margin-top: 0px;border-color:#ffffff;">
          <div class="input-group">
            <input type="text" name="keyword" class="form-control" placeholder="请输入关键字" maxlength="20" autocomplete="off">
            <span class="input-group-btn">
            <button class="btn btn-default btn-search" name="search" type="button" onclick="searchByKeyword();">搜索</button>
            </span>
          </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
          <li><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline">全部</a></li>
          <#list types as type>
            <li><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${type}">${type.value}</a></li>
          </#list>
        </ul>
        <table class="mobile-menu" style="border-spacing:10px 100px;">
	    <tbody>
	    	<tr>
	    		<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline">首页</a></a></td>
				<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${types[0]}">${types[0].value}</a></td>
				<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${types[1]}">${types[1].value}</a></td>
				<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${types[2]}">${types[2].value}</a></td>
				<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${types[3]}">${types[3].value}</a></td>
		    </tr>
		    <tr>
		    	<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${types[4]}">${types[4].value}</a></td>
	    		<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${types[5]}">${types[5].value}</a></td>
	    		<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${types[6]}">${types[6].value}</a></td>
	    		<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${types[7]}">${types[7].value}</a></td>
	    		<td><a data-cont="大国简史" title="大国简史" href="${contextPath}/timeline?type=${types[8]}">${types[8].value}</a></td>
		    </tr>
		</tbody></table>
      </div>
    </div>
  </nav>
</header>
<script>
function menu(){
	$('#header-navbar').toggle();
}
</script>