<div class="title">
        <h3>最新发布</h3>
        <div class="more">   
        <#list adMiddleList as advertisement>
            <a href="<#if advertisement.ad_link_url??>${advertisement.ad_link_url}<#else>javascript:void(0)</#if>" title="${advertisement.ad_desc}" target="_blank">${advertisement.ad_desc}</a> 
         </#list>
        </div>
</div>