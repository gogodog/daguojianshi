<html>
    <head>
        <meta charset="utf-8" />
        <title>大国简史</title>
        <#include "/front/common/header_static.ftl">
        <link rel="stylesheet" href="${contextPath}/front/timeline/timeline.css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Dancing+Script|Antic+Slab" />
    </head>
    
<body class="user-select single">
	<#assign page_name='show'>
    <#include "/front/common/header.ftl">
	
	
	<section class="container" style="top: 10%;">
  		<div class="content-wrap" style="min-height:inherit;">
    		<div class="content" style="margin-right:auto;">
				<div id="timeline"></div>
			</div>
	  	</div>
	</section>
	<!--<script src="${contextPath}/front/timeline/jquery.js"></script>-->
    <script src="${contextPath}/front/timeline/timeline-min.js"></script>
	<script src="${contextPath}/front/js/business/timeline.js"></script>
	<footer class="footer" style="position: absolute;bottom: 0;width: 100%;">
	  <div class="container" tyle="padding:2px;">
	    <p style="line-height:125%;">本站的部分内容来源于网络，若侵犯到您的利益，请联系站长删除！谢谢！Powered By [<a href="http://www.cwillow.com/" target="_blank" rel="nofollow" >大国简史</a>]<br></p>
	    <p style="line-height:125%;">联系方式：daguojianshi@163.com</p>
	    <p style="line-height:125%;">万柳网：京ICP备17022685号-1</p>
	  </div>
	  <div id="gotop" onclick="javascript:scroll(0,0)"><a class="gotop"></a></div>
	</footer>
    <#include "/front/common/footer_static.ftl">
</body>
</html>