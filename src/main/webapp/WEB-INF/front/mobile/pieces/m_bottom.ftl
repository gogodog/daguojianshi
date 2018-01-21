<div class="title title-mobile" style="border-bottom: 0;">
	<h3 style='text-align: left;'>
		<a>SimpleHistory |</a>
		<a class="small-title-mobile" style="top:0">Acc&bull;Dbs&bull;Fan&bull;TT</a>
	</h3>
</div>
<!-- 幻灯片 -->
<div class="carousel slide" data-ride="carousel" style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
    <div class="carousel-inner" role="listbox">
          <#list adList as ad>
             <div class="item <#if ad_index==0>active</#if>">
               <a href="${ad.ad_link_url}" title="${ad.ad_desc}" >
               <img src="${imageContextPath}${ad.ad_pic_url}" class="img-responsive" style="width:100%;height:100%"></a>
             </div>
          </#list>
          <div style="width:10%;color:white;background-color:rgba(0,0,0,0.2);text-align:center;position:absolute;right:1px;bottom:1px;z-index:99;">广告</div>
    	</div>
	</div>
</div>
