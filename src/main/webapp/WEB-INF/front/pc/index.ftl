<!doctype html>
<html lang="zh-CN">
<head>
    <#include "/front/common/pieces/header_static.ftl">
    <script>
		//页面统计
		var pageinfo_ = {};
		pageinfo_['page'] = 'index';
		pageinfo_['pagetype'] = 'list';//list or detail or other
		pageinfo_['pagedocids'] = '${pagedocids}';//文章id
		pageinfo_['pageadids'] = "${(pageadids)!''}";//广告id
		pageinfo_['pageid'] = '${pageid}';
		pageinfo_['channel'] = '${doctype.key}';
		window['pageinfo'] = pageinfo_;
		//
		var contextPath='${contextPath}';
		var keyword="${(keyword)!''}";
	</script>
</head>
<body class="user-select">
	<input type="hidden" id="doctype" value="${doctype}">
	<input type="hidden" id="contextPath" value="${contextPath}">
	<#include "/front/pc/ad/t1.ftl">
	<#assign page_name='index'>
    <#include "/front/common/pieces/header.ftl">
    <section class="container">
  <div class="content-wrap">
    <div class="content" id="content_t">
      <#include "/front/common/pieces/banner_index.ftl">
      <#include "/front/common/pieces/recommend_index.ftl">
      <#include "/front/common/pieces/list_title_ad.ftl">
      <#include "/front/common/pieces/pageutils.ftl">
    </div>
  </div>
  <aside class="sidebar">
    <div class="fixed">
      <#include "/front/common/pieces/count_connect.ftl">
      <#include "/front/common/pieces/search.ftl">
    </div>
     <#include "/front/common/pieces/newpl.ftl">
     <#include "/front/common/pieces/ad.ftl">
     <#include "/front/common/pieces/friendhref.ftl">
  </aside>
</section>
    <#include "/front/common/pieces/footer_title.ftl">
    <#include "/front/common/pieces/footer_static.ftl">
    <script src="/front/js/business/page.js?v=${staticVersion}"></script>
    <script src="/front/js/business/keyword.js?v=${staticVersion}"></script>
</body>
</html>
