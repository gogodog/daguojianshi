<!doctype html>
<html lang="zh-CN">
<head>
    <#include "/front/common/header_static.ftl">
    <script>
	    //页面统计
	    var pageinfo_ = {};
	    pageinfo_['page'] = 'show';
	    pageinfo_['pagetype'] = 'detail';//list or detail or other
	    pageinfo_['pagedocids'] = '${pagedocids}';//文章id
	    pageinfo_['pageadids'] = '${pageadids}';//广告id
	    pageinfo_['pageid'] = '10336267';
	    window['pageinfo'] = pageinfo_;
    </script>
</head>
<body class="user-select single">
    <#include "/front/common/header.ftl">
    <section class="container">
  <div class="content-wrap">
    <div class="content">
      <header class="article-header">
        <h1 class="article-title"><a href="javascript:void(0)" title="大国崛起的条件" >${articlescrap.title}</a></h1>
        <div class="article-meta"> <span class="item article-meta-time">
          <time class="time" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="发表时间：2016-10-14"><i class="glyphicon glyphicon-time"></i> ${articlescrap.start_time}</time>
          </span> <span class="item article-meta-source" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="来源：大国简史"><i class="glyphicon glyphicon-globe"></i> 大国简史</span> <span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="大国简史古军事主题"><i class="glyphicon glyphicon-list"></i> <a href="http://www.dgjs.com/list/mznetblog/" title="主题" > ${articlescrap.type.value}</a></span> <span id="show_visits" class="item article-meta-views" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="浏览量"><i class="glyphicon glyphicon-eye-open"></i> ${visits}</span></div>
      </header>
      <article class="article-content">
        <p>${articlescrap.content}</p>
      <div class="bdsharebuttonbox">
		<!-- <a href="#" class="bds_more" data-cmd="more"></a> -->
		<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
		<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
		<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
		<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
		<a href="#" class="bds_tieba" data-cmd="tieba" title="分享到百度贴吧"></a>
		<a href="#" class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a>
	  </div>
      <script>window._bd_share_config = { "common": { "bdSnsKey": {}, "bdText": "", "bdMini": "2", "bdMiniList": false, "bdPic": "", "bdStyle": "1", "bdSize": "32" }, "share": {} }; with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
      </article>
      <div class="article-tags">
      	标签：
      	<a href="http://www.dgjs.com/tags/list/2/" rel="tag" >美国</a>
      	<a href="http://www.dgjs.com/tags/list/3/" rel="tag" >军事独立</a>
      	<a href="http://www.dgjs.com/tags/list/4/" rel="tag" >强大国家</a>
      	<a href="http://www.dgjs.com/tags/list/5/" rel="tag" >大国战略</a>
        </div>
      <#include "/front/common/recommend.ftl">
      <#include "/front/common/response.ftl">
    </div>
  </div>
  <aside class="sidebar">
    <div class="fixed">
      <#include "/front/common/count_connect.ftl">
      <#include "/front/common/search.ftl">
    </div>
    <#include "/front/common/newpl.ftl">
	<#include "/front/common/ad.ftl">
  </aside>
</section>
    <#include "/front/common/footer_title.ftl">
    <#include "/front/common/footer_static.ftl">
</body>
</html>
