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
	</script>
</head>
<body class="user-select">
    <#include "/front/common/header.ftl">
    <section class="container">
  <div class="content-wrap">
    <div class="content">
      <#include "/front/common/banner_index.ftl">
      <#include "/front/common/recommend_index.ftl">
      <#include "/front/common/list_title_ad.ftl">
        <#list articlescrapPageInfo.objects as articlescrap>
        <article class="excerpt excerpt-1" style="">
            <a class="focus" href="${contextPath}/show/${articlescrap.id}" title="${articlescrap.title}" target="_blank" >
               <img class="thumb" data-original="${contextPath}/front/images/list/timg3.jpeg" src="${imageContextPath}${articlescrap.show_picture}" style="display: inline;">
            </a>
            <header>
              <a class="cat" href="http://www.dgjs.com/list/mznetblog/" title="${articlescrap.type.value}" >${articlescrap.type.value}<i></i></a>
              <h2><a href="${contextPath}/show/${articlescrap.id}" title="${articlescrap.title}" target="_blank" >${articlescrap.title}</a>
              </h2>
            </header>
            <p class="meta">
                <time class="time"><i class="glyphicon glyphicon-time"></i> <#if articlescrap.show_time??>${articlescrap.show_time?date}</#if></time>
                <time class="time"><i class="glyphicon glyphicon-time"></i> ${(articlescrap.start_time)!''}</time>
                <span class="views"><i class="glyphicon glyphicon-eye-open"></i> ${articlescrap.visits}</span>
            </p>
            <p class="note">${articlescrap.sub_content}</p>
        </article>
        </#list>
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
</body>
</html>
