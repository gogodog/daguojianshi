<#list rAEList as rea>
	   <article class="excerpt-minic excerpt-minic-index">
          <h2><span class="red">【推荐】</span><a target="_blank" href="${contextPath}/show/${rea.id}" title="头条" >${rea.title}</a>
          </h2>
          <p class="note">${rea.sub_content}</p>
       </article>
</#list>
        