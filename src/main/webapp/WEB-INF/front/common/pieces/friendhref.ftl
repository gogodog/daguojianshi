	<div class="widget widget_sentence">
      <h3>友情链接</h3>
      <div class="widget-sentence-link">
      <#list adBelowList as advertisement>
           <a href="<#if advertisement.ad_link_url??>${advertisement.ad_link_url}<#else>javascript:void(0)</#if>" title="广告植入" target="_blank" >${advertisement.ad_desc}</a>&nbsp;&nbsp;&nbsp;
      </#list>
      </div>
    </div>