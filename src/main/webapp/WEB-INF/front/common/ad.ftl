<#list advertisementList as advertisement>
  <#if advertisement.ad_position.key == 101>
     <div class="widget widget_sentence">
       <a href="<#if advertisement.ad_link_url??>${advertisement.ad_link_url}<#else>javascript:void(0)</#if>">
         <img style="width: 100%" src="${imageContextPath}${advertisement.ad_pic_url}">
       </a>
     </div>
  </#if>
  <#if advertisement.ad_position.key == 102>
     <div class="widget widget_sentence">
       <a href="<#if advertisement.ad_link_url??>${advertisement.ad_link_url}<#else>javascript:void(0)</#if>">
         <img style="width: 100%" src="${imageContextPath}${advertisement.ad_pic_url}">
       </a>
     </div>
  </#if>
</#list>