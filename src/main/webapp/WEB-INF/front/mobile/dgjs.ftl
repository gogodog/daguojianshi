<!doctype html>
<html lang="zh-CN">
<head>
    <#include "/front/common/pieces/header_static_mobile.ftl">
    <link rel="shortcut icon" href="http://photoswipe.com/dist/default-skin/default-skin.css?v=4.1.1-1.0.4">
    <link rel="shortcut icon" href="http://photoswipe.com/dist/photoswipe.css?v=4.1.1-1.0.4">
    <script>
		//页面统计
		var pageinfo_ = {};
		pageinfo_['page'] = 'index';
		pageinfo_['pagetype'] = 'list';//list or detail or other
		pageinfo_['pagedocids'] = '${pagedocids}';//文章id
		pageinfo_['pageadids'] = "${(pageadids)!''}";//广告id
		pageinfo_['pageid'] = '10336266';
		window['pageinfo'] = pageinfo_;
		//
		var contextPath='${contextPath}';
		var keyword="${(keyword)!''}";
	</script>
</head>
<body class="user-select">
	<#include "/front/mobile/ad/t1.ftl">
	<input type="hidden" id="doctype" value="${doctype}">
	<input type="hidden" id="contextPath" value="${contextPath}">
	<#assign page_name='dgjs'>
    <#include "/front/common/pieces/header.ftl">
    <section class="container">
	  <div class="content-wrap">
	    <div class="content" id="content_t">
	      <#include "/front/common/pieces/banner_index.ftl">
	      <#include "/front/common/pieces/recommend_index.ftl">
	      <#include "/front/mobile/pieces/total.ftl">
	      <#include "/front/mobile/pieces/shishi.ftl">
	      <#include "/front/mobile/pieces/zhengshi.ftl">
	      <#include "/front/mobile/pieces/dili.ftl">
	      <#include "/front/mobile/pieces/yeshi.ftl">
	      <#include "/front/mobile/pieces/renwu.ftl">
	    </div>
	  </div>
	  <!-- 防止移动端页面抖动 -->
	  <aside class="sidebar"></aside>
	</section>
    <#include "/front/common/pieces/footer_title.ftl">
    <#include "/front/common/pieces/footer_static.ftl">
   
    <script src="${contextPath}/front/js/jquery-1.11.1.min.js"></script>
    <script src="${contextPath}/front/js/business/m_index.js"></script>
    <script src="${contextPath}/front/js/business/keyword.js"></script>
</body>
</html>
