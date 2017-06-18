<!doctype html>
<html lang="zh-CN">
<head>
    <#include "/front/common/header_static.ftl">
    <script>
		//页面统计
		var pageinfo_ = {};
		pageinfo_['page'] = 'index';
		pageinfo_['pagetype'] = 'list';//list or detail or other
		pageinfo_['pagedocids'] = '${pagedocids}';//文章id
		pageinfo_['pageadids'] = "${(pageadids)!''}";//广告id
		pageinfo_['pageid'] = '10336266';
		window['pageinfo'] = pageinfo_;
		var contextPath='${contextPath}';
	</script>
</head>
<body class="user-select">
	<input type="hidden" id="doctype" value="${doctype}">
	<input type="hidden" id="contextPath" value="${contextPath}">
    <#include "/front/common/header.ftl">
    <section class="container">
  <div class="content-wrap">
    <div class="content" id="content_t">
      <#include "/front/common/banner_index.ftl">
      <#include "/front/common/recommend_index.ftl">
      <#include "/front/common/list_title_ad.ftl">
      <#include "/front/common/pageutils.ftl">
    </div>
  </div>
  <aside class="sidebar">
    <div class="fixed">
      <#include "/front/common/count_connect.ftl">
      <#include "/front/common/search.ftl">
    </div>
     <#include "/front/common/newpl.ftl">
     <#include "/front/common/ad.ftl">
     <#include "/front/common/friendhref.ftl">
  </aside>
</section>
    <#include "/front/common/footer_title.ftl">
    <#include "/front/common/footer_static.ftl">
    <script src="${contextPath}/common/page.js"></script>
</body>
</html>
