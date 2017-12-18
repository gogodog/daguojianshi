<!doctype html>
<html lang="zh-CN">
<head>
    <#include "/front/common/pieces/header_static.ftl">
    <script>
	    var contextPath='${contextPath}'
    </script>
</head>
<body class="user-select single">
    <#assign page_name='show'>
    <#include "/front/common/pieces/header.ftl">
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
            <span id="show_visits" class="item article-meta-views" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="浏览量"><i class="glyphicon glyphicon-eye-open"></i> ${visits}</span><#if articlescrap.start_time??><a href="/timeline?articlescrapId=${articlescrap.id}" style="float: right;">查看时轴&gt;&gt;</a></#if></div>
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
      </article>
      <div class="article-tags">
      	标签：
   	    <a href="javascript:void(0)" rel="tag" >${(articlescrap.typeValue)!''}</a>
      	<#list articlescrap.keywords as keywords>
      	   <a href="javascript:void(0)" rel="tag" >${(keywords)!''}</a>
      	</#list>
        </div>
    </div>
  </div>
  <aside class="sidebar">
    <div class="fixed">
      <#include "/front/common/pieces/count_connect.ftl">
      <#include "/front/common/pieces/search.ftl">
    </div>
    <#include "/front/common/pieces/newpl.ftl">
	<#include "/front/common/pieces/ad.ftl">
  </aside>
</section>
    <#include "/front/common/pieces/footer_title.ftl">
</body>
</html>
