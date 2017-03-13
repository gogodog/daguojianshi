		<div id="focusslide" class="carousel slide" data-ride="carousel">
	        <ol class="carousel-indicators">
	          <li data-target="#focusslide" data-slide-to="0" class="active"></li>
	          <li data-target="#focusslide" data-slide-to="1"></li>
	          <li data-target="#focusslide" data-slide-to="2"></li>
	          <li data-target="#focusslide" data-slide-to="3"></li>
	        </ol>
	        <div class="carousel-inner" role="listbox">
	              <#list carouselList as carousel>
	                 <div class="item<#if carousel_index==0> active</#if>">
	                    <a href="${carousel.link_url}" target="_blank" title="${carousel.image_desc}" >
			            <img src="${carousel.image_url}" alt="dgjs" class="img-responsive"></a>
		              </div>
		          </#list>
	        </div>
	        <a class="left carousel-control" href="#focusslide" role="button" data-slide="prev" rel="nofollow"> 
		        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> 
		        <span class="sr-only">上一个</span> 
	        </a> 
	        <a class="right carousel-control" href="#focusslide" role="button" data-slide="next" rel="nofollow"> 
		        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 
		        <span class="sr-only">下一个</span> 
	        </a> 
        </div>