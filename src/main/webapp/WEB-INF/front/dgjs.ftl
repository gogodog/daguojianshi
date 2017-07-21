<!doctype html>
<html lang="zh-CN">
<head>
    <#include "/front/common/header_static_mobile.ftl">
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
	<#assign page_name='index'>
    <#include "/front/common/header.ftl">
    <section class="container">
	  <div class="content-wrap">
	    <div class="content" id="content_t">
	      <#include "/front/common/banner_index.ftl">
	      <#include "/front/common/recommend_index.ftl">
	      <#include "/front/theme/mobile/shishi.ftl">
	      <#include "/front/theme/mobile/zhengshi.ftl">
	      <#include "/front/theme/mobile/dili.ftl">
	      <#include "/front/theme/mobile/yeshi.ftl">
	      <#include "/front/theme/mobile/renwu.ftl">
	    </div>
	  </div>
	  <!-- 防止移动端页面抖动 -->
	  <aside class="sidebar"></aside>
	</section>
    <#include "/front/common/footer_title_mobile.ftl">
    <#include "/front/common/footer_static.ftl">
</body>
</html>
