<div class="relates">
        <div class="title">
          <h3>用户评价</h3>
        </div>
        <ul>
        <div class="judge-tags">
        评价：
            <#list judgeLevels as jl>
              <a href="javascript:void(0)" rel="tag" onclick="judge('${articlescrap.id}','${jl}');">${jl.value}</a>
            </#list>
        </div>
        </ul>
      </div>