<div class="widget widget_hot">
          <h3>最新评论文章</h3>
          	<ul>
          	 <#list commentsArticlescrapList as articlescrap>
				<li>
					<a title="${articlescrap.title}" href="/show/${articlescrap.id}" >
						<span class="thumbnail">
							<img class="thumb" src="${articlescrap.pictures[0]}" style="display: block;">
						</span>
						<span class="text">${articlescrap.title}</span>
						<span class="muted"><i class="glyphicon glyphicon-time"></i>${(articlescrap.start_time)!'无'}</span>
					</a>
				</li>
			 </#list>
			</ul>
</div>