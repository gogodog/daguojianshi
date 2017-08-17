<#list adPicList as advertisement>
     <div class="widget widget_sentence">
       <a href="<#if advertisement.ad_link_url??>${advertisement.ad_link_url}<#else>javascript:void(0)</#if>" target="_blank">
         <img style="width: 100%" src="${imageContextPath}${advertisement.ad_pic_url}">
       </a>
     </div>
</#list>