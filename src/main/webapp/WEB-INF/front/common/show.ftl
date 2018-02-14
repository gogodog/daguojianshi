<!doctype html>
<html lang="zh-CN">
<head>
    <#include "/front/common/pieces/header_static.ftl">
    <script>
	    //页面统计
	    var pageinfo_ = {};
	    pageinfo_['page'] = 'show';
	    pageinfo_['pagetype'] = 'detail';//list or detail or other
	    pageinfo_['pagedocids'] = '${pagedocids}';//文章id
	    pageinfo_['pageadids'] = '${pageadids}';//广告id
	    pageinfo_['pageid'] = '${pageid}';
	    window['pageinfo'] = pageinfo_;
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
           <p style="text-align:center;text-indent:0px;font-weight: 700;">
    	     - 大国简史 -
           </p>
    	   <p style="text-align:center;text-indent:0px;">
    	 	 Country Of History
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
		<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
		<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
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
      <#include "/front/common/pieces/recommend.ftl">
      <#include "/front/common/pieces/response.ftl">
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
    <#include "/front/common/pieces/footer_static.ftl">
    <script src="/front/js/business/keyword.js?v=${staticVersion}"></script>
    <script src="/front/js/business/show.js?v=${staticVersion}"></script>
    <script src="//res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script>
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '', // 必填，公众号的唯一标识
        timestamp: , // 必填，生成签名的时间戳
        nonceStr: '', // 必填，生成签名的随机串
        signature: '',// 必填，签名，见附录1
        jsApiList: ["onMenuShareTimeline","onMenuShareAppMessage"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function(){
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    });

    wx.error(function(res){
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
    });

    //分享到朋友圈
    wx.onMenuShareTimeline({
        title: '', // 分享标题
        link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: '', // 分享图标
        success: function () {
        // 用户确认分享后执行的回调函数
        },
        cancel: function () {
        // 用户取消分享后执行的回调函数
        }
    });

    //分享好朋友
    wx.onMenuShareAppMessage({
        title: '', // 分享标题
        desc: '', // 分享描述
        link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: '', // 分享图标
        type: '', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
        // 用户确认分享后执行的回调函数
        },
        cancel: function () {
        // 用户取消分享后执行的回调函数
        }
    });

    </script>
</body>
</html>
