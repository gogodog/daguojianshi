<div class="relates">
        <div class="title">
          <h3>相关推荐</h3>
        </div>
        <ul>
        <#list rAEList as rea>
          <li><a href="${contextPath}/show/${rea.id}" title="${rea.title}" >${rea.title}</a></li>
        </#list>
        </ul>
      </div>