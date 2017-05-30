<div class="widget widget_hot">
          <h3>最新评论文章</h3>
          	<ul>
          	 <#list commentsArticlescrapList as articlescrap>
				<li>
					<a title="${articlescrap.title}" href="${contextPath}/show/${articlescrap.id}" >
						<span class="thumbnail">
							<img class="thumb" data-original="${contextPath}/front/images/list/timg2.jpeg" src="${imageContextPath}${articlescrap.show_picture}" alt="美国的兴邦之史-富兰克林"  style="display: block;">
						</span>
						<span class="text">${articlescrap.title}</span>
						<span class="muted"><i class="glyphicon glyphicon-time"></i><#if articlescrap.show_time??>${articlescrap.show_time?date}</#if></span>
						<span class="muted"><i class="glyphicon glyphicon-eye-open"></i>88</span>
					</a>
				</li>
			 </#list>
			</ul>
</div>