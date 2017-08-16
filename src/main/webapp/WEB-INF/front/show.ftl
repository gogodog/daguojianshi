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
	    var contextPath='${contextPath}'
    </script>
</head>
<body class="user-select single">
    <#assign page_name='show'>
    <#include "/front/common/header.ftl">
    <section class="container">
  <div class="content-wrap">
    <div class="content">
      <header class="article-header">
        <h1 class="article-title"><a href="javascript:void(0)" title="大国崛起的条件" >${articlescrap.title}</a></h1>
        <div class="article-meta"> <span class="item article-meta-time">
          <time class="time" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="发表时间：2016-10-14"><i class="glyphicon glyphicon-time"></i><#if articlescrap.start_time??> ${articlescrap.start_time}<#else> 无</#if></time>
          </span> <span class="item article-meta-source" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="来源：大国简史"><i class="glyphicon glyphicon-globe"></i> 大国简史</span> <span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="大国简史古军事主题"><i class="glyphicon glyphicon-list"></i> 
            <a href="javascript:void(0)" title="主题" >
            ${articlescrap.typeValue}
            </a></span> 
            <span id="show_visits" class="item article-meta-views" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="浏览量"><i class="glyphicon glyphicon-eye-open"></i> ${visits}</span><#if articlescrap.start_time??><a href="${contextPath}/timeline?articlescrapId=${articlescrap.id}" style="float: right;">查看时轴&gt;&gt;</a></#if></div>
      </header>
      <article class="article-content">
        <p>${articlescrap.content}</p>
        <div style="margin: 10px 0">
           <p style="text-align:center;text-indent:0px;">
    	     - 大国简史 -
           </p>
    	   <p style="text-align:center;text-indent:0px;">
    	 	 Country Of History
    	   </p>
    	   <p style="text-align:center;text-indent:0px;"> 
    		 <strong><img src="http://www.cwillow.com/images/editor/p1/20170531054816777978.jpg" alt=""><br>  </strong> 
    	   </p>
    	   <p style="text-align:center;text-indent:0px;">
    		 <strong>来自中国的世界史</strong> 
    	   </p>
    	   <p style="text-align:center;text-indent:0px;">
    		 人类简史的时间轴
    	   </p>
    	   <p style="text-align:center;text-indent:0px;">
    		 价值 &nbsp; 历史 &nbsp; 年代
    	   </p>
        </div>
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
   	    <a href="javascript:void(0)" rel="tag" >${(articlescrap.typeValue)!''}</a>
      	<#list articlescrap.keywords as keywords>
      	   <a href="javascript:void(0)" rel="tag" >${(keywords)!''}</a>
      	</#list>
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
    <script src="${contextPath}/common/keyword.js"></script>
    <script src="${contextPath}/front/js/business/show.js"></script>
</body>
</html>
