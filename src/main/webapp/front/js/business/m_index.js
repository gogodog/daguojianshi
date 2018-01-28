window.onload=function(){
	total();
	shishi();
	zhengshi();
	dili();
	renwu();
	yeshi();
};

function shishi(){
	jQuery.ajax({
        type:"POST",
        url: contextPath+"/idxAfis",
        async: true,
        dataType: "json",
        success:function(data) {
            var first = data.first;
            var second = data.second;
            var moreLink = data.moreLink;
            var visits = data.visits;
            var content="";
            if(first!=null && first.length>0){
            	for(var i=0;i<first.length;i++){
                	var item = first[i];
                	content+=shishi1.replace(rplc("aId"),item.aid)
      		                  .replace(rplc("picUrl"),item.pictures[0])
      		                  .replace(rplc("atitle"),item.title)
      		                  .replace(rplc("start_time"),item.start_time==null||item.start_time.length==0?'无':item.start_time)
      		                  .replace(rplc("visits"),visits[item.aid])
      		                  .replace(rplc("contextPath"),contextPath);
                }
            }
            if(second!=null && second.length>0){
            	for(var i=0;i<second.length;i++){
                	var item = second[i];
                	content+=shishi2.replace(rplc("aId"),item.aid)
      		                  .replace(rplc("atitle"),item.title) 
      		                  .replace(rplc("sub_content"),item.sub_content)
      		                  .replace(rplc("contextPath"),contextPath);
                }
            }
            $("#shishi").html(content);
            $("#shishimore").attr("href",moreLink);
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

function zhengshi(){
	jQuery.ajax({
        type:"POST",
        url: contextPath+"/idxhstry",
        async: true,
        dataType: "json",
        success:function(data) {
        	var ad = data.ad;
        	var first = data.first;
            var second = data.second;
            var moreLink = data.moreLink;
            var content="";
            
            if(ad!=null && ad.length>0){
            	 for(var i=0;i<ad.length;i++){
                 	var item = ad[i];
                 	content+=advertisement.replace(rplc("adUrl"),item.link)
                                 .replace(rplc("picUrl1"),item.pictures[0])
                                 .replace(rplc("picUrl2"),item.pictures[1])
                                 .replace(rplc("picUrl3"),item.pictures[2]);
                 }
            }
            if(first!=null && first.length>0){
            	for(var i=0;i<first.length;i++){
                	var item = first[i];
                	content+=zhengshi1.replace(rplc("aId"),item.aid)
      		                   .replace(rplc("contextPath"),contextPath)
      		                   .replace(rplc("sub_content"),item.sub_content)
      		                   .replace(rplc("picUrl1"),item.pictures[0])
      		                   .replace(rplc("picUrl2"),item.pictures[1])
      		                   .replace(rplc("picUrl3"),item.pictures[2]);
                }
            }
            if(second!=null && second.length>0){
            	for(var i=0;i<second.length;i++){
                	var item = second[i];
                	content+=zhengshi2.replace(rplc("aId"),item.aid)
       	       	               .replace(rplc("sub_content"),item.sub_content)
       	       	               .replace(rplc("atitle"),item.title)
       	       	               .replace(rplc("contextPath"),contextPath);
                }
            }
            $("#zhengshi").html(content);
            $("#zhengshimore").attr("href",moreLink);
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

function dili(){
	jQuery.ajax({
        type:"POST",
        url: contextPath+"/idxgogry",
        async: true,
        dataType: "json",
        success:function(data) {
        	var first = data.first;
            var second = data.second;
            var ad = data.ad;
            var moreLink = data.moreLink;
            var visits = data.visits;
            var content="";
            if(ad!=null && ad.length>0){
            	for(var i=0;i<ad.length;i++){
                	var item = ad[i];
                	content+=advertisement.replace(rplc("adUrl"),item.link)
                                .replace(rplc("picUrl1"),item.pictures[0])
                                .replace(rplc("picUrl2"),item.pictures[1])
                                .replace(rplc("picUrl3"),item.pictures[2]);
                }
            }
            if(first!=null && first.length>0){
            	for(var i=0;i<first.length;i++){
                	var item = first[i];
                	content+=dili1.replace(rplc("aId"),item.aid)
      		                    .replace(rplc("contextPath"),contextPath)
      		                    .replace(rplc("picUrl1"),item.pictures[0])
      		                    .replace(rplc("picUrl2"),item.pictures[1])
      		                    .replace(rplc("picUrl3"),item.pictures[2]);
                }
            }
            if(second!=null && second.length>0){
            	for(var i=0;i<second.length;i++){
                	var item = second[i];
                	content+=dili2.replace(rplc("aId"),item.aid)
       	       	                .replace(rplc("atitle"),item.title)
       	       	                .replace(rplc("picUrl"),item.pictures[0])
       	       	                .replace(rplc("start_time"),item.start_time==null||item.start_time.length==0?'无':item.start_time)
       	       	                .replace(rplc("visits"),visits[item.aid])
       	                        .replace(rplc("contextPath"),contextPath);
                }
            }
            $("#dili").html(content);
            $("#dilimore").attr("href",moreLink);
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

function renwu(){
	jQuery.ajax({
        type:"POST",
        url: contextPath+"/idxpsn",
        async: true,
        dataType: "json",
        success:function(data) {
        	var first = data.first;
            var second = data.second;
            var moreLink = data.moreLink;
            var visits = data.visits;
            var content="";
            var temp="";
            
            if(first!=null && first.length>0){
            	for(var i=0;i<first.length;i++){
                	var item = first[i];
                	content+=renwu1.replace(rplc("aId"),item.aid)
      		                  .replace(rplc("contextPath"),contextPath)
      		                  .replace(rplc("picUrl"),item.pictures[0])
      		                  .replace(rplc("start_time"),item.start_time==null||item.start_time.length==0?'无':item.start_time)
      		                  .replace(rplc("visits"),visits[item.aid])
      		                  .replace(rplc("atitle"),item.title);
                }
            }
            if(second!=null && second.length>0){
            	for(var i=0;i<second.length;i++){
                	var item = second[i];
                	if(i%2==0){
            			temp="";
            			temp+=renwu2.replace(rplc("aId1"),item.aid)
            			      .replace(rplc("atitle1"),item.title)
    		                  .replace(rplc("contextPath"),contextPath);
            		}else{
            			temp=temp.replace(rplc("aId2"),item.aid)
          			          .replace(rplc("atitle2"),item.title)
    	                      .replace(rplc("contextPath"),contextPath);
            			content+=temp;
            		}
                }
            }
            $("#renwu").html(content);
            $("#renwumore").attr("href",moreLink);
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

function yeshi(){
	jQuery.ajax({
        type:"POST",
        url: contextPath+"/idxuofcl",
        async: true,
        dataType: "json",
        success:function(data) {
        	var first = data.first;
            var second = data.second;
            var moreLink = data.moreLink;
            var carouselList=data.carouselList;
            var visits = data.visits;
            var content="";
            var temp="";
            
            if(first!=null && first.length>0){
            	for(var i=0;i<first.length;i++){
                	var item = first[i];
                	content+=yeshi1.replace(rplc("aId"),item.aid)
      		                  .replace(rplc("contextPath"),contextPath)
      		                  .replace(rplc("atitle"),item.title);
                }
            }
            if(second!=null && second.length>0){
            	for(var i=0;i<second.length;i++){
                	var item = second[i];
                	if(i%2==1){
            			temp="";
            			temp+=yeshi2.replace(rplc("aId1"),item.aid)
            			  .replace(rplc("atitle1"),item.title)
    		              .replace(rplc("contextPath"),contextPath);
            		}else{
            			temp=temp.replace(rplc("aId2"),item.aid)
          			      .replace(rplc("atitle2"),item.title)
    	                  .replace(rplc("contextPath"),contextPath);
            			content+=temp;
            		}
                }
            }
            $("#yeshi").html(content);
            $("#yeshimore").attr("href",moreLink);
            var crsl = "";
            if(carouselList!=null && carouselList.length>0){
            	for(var i=0;i<carouselList.length;i++){
                	var carousel = carouselList[i];
                	var activeHtml = "";
                	if(i==0){
                		activeHtml = " active";
                	}
                	crsl+=yeshicrsl.replace(rplc("linkUrl"),carousel.link_url)
                	         .replace(rplc("contextPath"),contextPath)
                	         .replace(rplc("image_url"),carousel.image_url)
                	         .replace(rplc("active"),activeHtml)
                	         .replace(rplc("image_desc"),carousel.image_desc);
                }
            }
            $("#yeshicrsl").html(crsl);
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

function total(){
	jQuery.ajax({
        type:"POST",
        url: contextPath+"/idxtotal",
        async: true,
        dataType: "json",
        success:function(data) {
        	var first = data.first;
            var second = data.second;
            var moreLink = data.moreLink;
            var visits = data.visits;
            var content="";
            if(first!=null && first.length>0){
            	for(var i=0;i<first.length;i++){
                	var item = first[i];
                	content+=total1.replace(rplc("aId"),item.aid)
      		                  .replace(rplc("picUrl"),item.pictures[0])
      		                  .replace(rplc("atitle"),item.title)
      		                  .replace(rplc("start_time"),item.start_time==null||item.start_time.length==0?'无':item.start_time)
      		                  .replace(rplc("visits"),visits[item.aid])
      		                  .replace(rplc("atype"),item.aType)
      		                  .replace(rplc("contextPath"),contextPath);
                }
            }
            if(second!=null && second.length>0){
            	for(var i=0;i<second.length;i++){
                	var item = second[i];
                	content+=total2.replace(rplc("aId"),item.aid)
      		                  .replace(rplc("atitle"),item.title) 
      		                  .replace(rplc("atype"),item.aType)
      		                  .replace(rplc("contextPath"),contextPath);
                }
            }
            $("#total").html(content);
            $("#totalmore").attr("href",moreLink);
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

function rplc(str){
	return new RegExp(str,"gm");
}

var shishi1="<article class=\"excerpt listright\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<a class=\"focus\" href=\"javascript:void(0)\">"
	   +"<img class=\"thumb\" src=\""+defaultImage+"\" lazy-joy='picUrl' style=\"display:inline;\">"
	   +"</a><header>"
	   +"<a class=\"cat\" href=\"javascript:void(0)\">时事<i></i></a></h2>"
	   +"<h2><a href=\"javascript:void(0)\">atitle</a></header>"
       +"<p class=\"meta\">"
	   +"<time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i>start_time</time>"	
	   +"<span class=\"views\"><i class=\"glyphicon glyphicon-eye-open\"></i> visits</span>"
	   +"</p></article>";
var shishi2="<article class=\"excerpt listtext\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<header><h2><a href=\"javascript:void(0)\" >&bull;&nbsp atitle</a></h2></header>"
	   +"<p class=\"note\" style=\"display:block\">sub_content</p></article>";
var zhengshi1="<article class=\"excerpt threeimg\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<div class='diliimg'><img class=\"itemimg\" src=\""+defaultImage+"\" lazy-joy='picUrl1'></div>"
	   +"<div class='diliimg'><img class=\"itemimg\" src=\""+defaultImage+"\" lazy-joy='picUrl2'></div>"
	   +"<div class='diliimg'><img class=\"itemimg\" src=\""+defaultImage+"\" lazy-joy='picUrl3'></div>"
	   +"<p style=\"text-align: left;padding-right: 10px;color: cadetblue;font-size: .5em;\">sub_content</p></article>";
var zhengshi2="<article class=\"excerpt listtext\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<header><h2><a href=\"javascript:void(0)\" >&bull;&nbsp atitle</a></h2></header>"
	   +"<p class=\"note\" style=\"display:block\">sub_content</p></article>";
var dili1="<article class=\"excerpt threeimg\" onclick=\"location.href='contextPath/show/aId'\">"
       +"<div class='diliimg'><img class=\"itemimg\" src=\""+defaultImage+"\" lazy-joy='picUrl1'></div>"
       +"<div class='diliimg'><img class=\"itemimg\" src=\""+defaultImage+"\" lazy-joy='picUrl2'></div>"
       +"<div class='diliimg'><img class=\"itemimg\" src=\""+defaultImage+"\" lazy-joy='picUrl3'></div></article>";
var dili2="<article class=\"excerpt listright\" onclick=\"location.href='contextPath/show/aId'\">"
       +"<a class=\"focus\" href=\"javascript:void(0)\">"
       +"<img class=\"thumb\" src=\""+defaultImage+"\" lazy-joy='picUrl' style=\"display:inline;\"></a>"
       +"<header><a class=\"cat\" href=\"javascript:void(0)\">地理<i></i></a>"
       +"<h2><a class=\"dilitxt\" href=\"javascript:void(0)\">atitle</a></h2></header>"
       +"<p class=\"meta\"><time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i> start_time</time>"
       +"<span class=\"views\"><i class=\"glyphicon glyphicon-eye-open\"></i> visits</span>"
	   +"</p></article>";
var renwu1="<article class=\"excerpt listright\" onclick=\"location.href='contextPath/show/aId'\">"
       +"<a class=\"focus\" href=\"javascript:void(0)\">"
       +"<img class=\"thumb\" src=\""+defaultImage+"\" lazy-joy='picUrl' style=\"display:inline;\">"
	   +"</a><header><a class=\"cat\" href=\"javascript:void(0)\">人物<i></i></a>"
	   +"<h2><a class=\"dilitxt\" href=\"javascript:void(0)\">atitle</a></h2>"
	   +"</header><p class=\"meta\"><time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i>start_time</time>"
	   +"<span class=\"views\"><i class=\"glyphicon glyphicon-eye-open\"></i> visits</span>"
       +"</p></article>";
var renwu2="<article class=\"excerpt twohref\">"
       +"<div class=\"inline2div\"><a href='contextPath/show/aId1'>★&nbspatitle1</a>"
       +"<a href='contextPath/show/aId2'>★&nbspatitle2</a></div></article>";
var yeshi1="<article class=\"excerpt listtxt\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<header><a class=\"cat\" href=\"javascript:void(0)\">野史<i></i></a>"
	   +"<h2><a class=\"dilitxt\" href=\"javascript:void(0)\" >atitle</a></h2></header></article>";
var yeshi2="<article class=\"excerpt twohref\"><div class=\"inline2div\">"
	   +"<a href='contextPath/show/aId1'>&Xi;&nbspatitle1</a><a href='contextPathlinkUrl'>&Xi;&nbspatitle2</a></div></article>";
var yeshicrsl="<div class=\"itemactive\"><a href=\"linkUrl\">"
       +"<img src=\"image_url\" alt=\"cwillow\" class=\"img-responsive\" style=\"width:100%;height:100%\"></a>"         
       +"<a class=\"banner-title\" href=\"linkUrl\">image_desc</a></div>";
var total1="<article class=\"excerpt listright\" onclick=\"location.href='contextPath/show/aId'\">"
       +"<a class=\"focus\" href=\"javascript:void(0)\"><img class=\"thumb\" src=\""+defaultImage+"\" lazy-joy='picUrl' style=\"display:inline;\"></a>"
       +"<header><a class=\"cat\" href=\"javascript:void(0)\">atype<i></i></a>"
       +"<h2><a class=\"dilitxt\" href=\"javacript:void(0)\">atitle</a></h2></header>"
       +"<p class=\"meta\"><time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i>start_time</time>"
       +"<span class=\"views\"><i class=\"glyphicon glyphicon-eye-open\"></i> visits</span>"
	   +"</p></article>";
var total2="<article class=\"excerpt listtxt\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<header><a class=\"cat\" href=\"javascript:void(0)\">atype<i></i></a><h2>"
	   +"<a class=\"dilitxt\" href=\"javascript:void(0)\" >atitle</a>"
	   +"</h2></header></article>";

var advertisement="<article class=\"excerpt threeimg\" onclick=\"location.href='adUrl'\">"
    +"<div class='diliimg'><img class=\"itemimg\" src=\"picUrl1\"></div>"
    +"<div class='diliimg'><img class=\"itemimg\" src=\"picUrl2\"></div>"
    +"<div class='diliimg'><img class=\"itemimg\" src=\"picUrl3\"></div></article>";