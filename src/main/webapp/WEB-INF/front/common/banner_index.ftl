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
	                    <a href="${carousel.link_url}" title="${carousel.image_desc}" >
	                    <!--[测试轮播图] http://www.cwillow.com/images/carousel/p_100/20170702054527046417.jpg -->
			            <img src="${imageContextPath}${carousel.image_url}" alt="dgjs" class="img-responsive" style="width:100%;height:100%"></a>
			            <a class="banner-title" href="${carousel.link_url}">${carousel.image_desc}</a>
		              </div>
		          </#list>
	        </div>
	        <a id="prev" class="left carousel-control hidepn-btn" href="#focusslide" role="button" data-slide="prev" rel="nofollow"> 
		        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> 
		        <span class="sr-only">上一个</span> 
	        </a> 
	        <a id="next" class="right carousel-control hidepn-btn" href="#focusslide" role="button" data-slide="next" rel="nofollow"> 
		        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 
		        <span class="sr-only">下一个</span> 
	        </a> 
        </div>
        <script>
          $(function(){
	            var myElement= document.getElementById('focusslide')
	            var hm=new Hammer(myElement);
	            hm.on("swipeleft",function(){
	                $('#focusslide').carousel('next')
	            })
	            hm.on("swiperight",function(){
	                $('#focusslide').carousel('prev')
	            })
	        })
	  </script>