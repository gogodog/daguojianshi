window.onload=function(){
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
            var list = data.list;
            var moreLink = data.moreLink;
            var position1 = 2;
            var content="";
            for(var i=0;i<list.length;i++){
            	var item = list[i];
            	if(i < position1){
            		content+=shishi1.replace(rplc("aId"),item.aid)
            		      .replace(rplc("picUrl"),item.pictures[0])
            		      .replace(rplc("atitle"),item.title)
            		      .replace(rplc("start_time"),item.start_time==null||item.start_time.length==0?'无':item.start_time)
            		      .replace(rplc("contextPath"),contextPath);
            	}else{
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
            var list = data.list;
            var moreLink = data.moreLink;
            var position1 = 1;
            var content="";
            for(var i=0;i<list.length;i++){
            	var item = list[i];
            	if(i < position1){
            		content+=zhengshi1.replace(rplc("aId"),item.aid)
            		      .replace(rplc("contextPath"),contextPath)
            		      .replace(rplc("sub_content"),item.sub_content)
            		      .replace(rplc("picUrl1"),item.pictures[0])
            		      .replace(rplc("picUrl2"),item.pictures[1])
            		      .replace(rplc("picUrl3"),item.pictures[2]);
            	}else{
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
            var list = data.list;
            var moreLink = data.moreLink;
            var position1 = 1;
            var content="";
            for(var i=0;i<list.length;i++){
            	var item = list[i];
            	if(i < position1){
            		content+=dili1.replace(rplc("aId"),item.aid)
            		      .replace(rplc("contextPath"),contextPath)
            		      .replace(rplc("picUrl1"),item.pictures[0])
            		      .replace(rplc("picUrl2"),item.pictures[1])
            		      .replace(rplc("picUrl3"),item.pictures[2]);
            	}else{
            		content+=dili2.replace(rplc("aId"),item.aid)
            	       	 .replace(rplc("atitle"),item.title)
            	       	 .replace(rplc("picUrl"),item.pictures[0])
            	       	 .replace(rplc("start_time"),item.start_time==null||item.start_time.length==0?'无':item.start_time)
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
            var list = data.list;
            var moreLink = data.moreLink;
            var position1 = 4;
            var content="";
            var temp="";
            for(var i=0;i<list.length;i++){
            	var item = list[i];
            	if(i < position1){
            		content+=renwu1.replace(rplc("aId"),item.aid)
            		      .replace(rplc("contextPath"),contextPath)
            		      .replace(rplc("picUrl"),item.pictures[0])
            		      .replace(rplc("start_time"),item.start_time==null||item.start_time.length==0?'无':item.start_time)
            		      .replace(rplc("atitle"),item.title);
            	}else{
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
            var list = data.list;
            var moreLink = data.moreLink;
            var carouselList=data.carouselList;
            var position1 = 3;
            var content="";
            var temp="";
            for(var i=0;i<list.length;i++){
            	var item = list[i];
            	if(i < position1){
            		content+=renwu1.replace(rplc("aId"),item.aid)
            		      .replace(rplc("contextPath"),contextPath)
            		      .replace(rplc("atitle"),item.title);
            	}else{
            		if(i%2==1){
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
            $("#yeshi").html(content);
            $("#yeshimore").attr("href",moreLink);
            var crsl = "";
            for(var i=0;i<carouselList.length;i++){
            	var carousel = carouselList[i];
            	crsl+=yeshicrsl.replace(rplc("linkUrl"),carousel.link_url)
            	         .replace(rplc("contextPath"),contextPath)
            	         .replace(rplc("picUrl"),carousel.image_url)
            	         .replace(rplc("image_desc"),carousel.image_desc);
            }
            $("#yeshicrsl").html(crsl);
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
	   +"<img class=\"thumb\" src=\"contextPathpicUrl\" style=\"display:inline;\">"
	   +"</a><header>"
	   +"<a class=\"cat\" href=\"javascript:void(0)\">国内<i></i></a></h2>"
	   +"<h2><a href=\"javascript:void(0)\">atitle</a></header>"
       +"<p class=\"meta\">"
	   +"<time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i>start_time</time>"	
	   +"</p></article>";
var shishi2="<article class=\"excerpt listtext\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<header><h2><a href=\"javascript:void(0)\" >&bull;&nbsp atitle</a></h2></header>"
	   +"<p class=\"note\" style=\"display:block\">sub_content</p></article>";
var zhengshi1="<article class=\"excerpt threeimg\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<div class='diliimg'><img class=\"itemimg\" src=\"contextPathpicUrl1\"></div>"
	   +"<div class='diliimg'><img class=\"itemimg\" src=\"contextPathpicUrl2\"></div>"
	   +"<div class='diliimg'><img class=\"itemimg\" src=\"contextPathpicUrl3\"></div>"
	   +"<p style=\"text-align: right;padding-right: 10px;color: cadetblue;font-size: .5em;\">sub_content</p></article>";
var zhengshi2="<article class=\"excerpt listtext\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<header><h2><a href=\"javascript:void(0)\" >&bull;&nbsp atitle</a></h2></header>"
	   +"<p class=\"note\" style=\"display:block\">sub_content</p></article>";
var dili1="<article class=\"excerpt threeimg\" onclick=\"location.href='contextPath/show/aId'\">"
       +"<div class='diliimg'><img class=\"itemimg\" src=\"contextPathpicUrl1\"></div>"
       +"<div class='diliimg'><img class=\"itemimg\" src=\"contextPathpicUrl2\"></div>"
       +"<div class='diliimg'><img class=\"itemimg\" src=\"contextPathpicUrl3\"></div></article>";
var dili2="<article class=\"excerpt listright\" onclick=\"location.href='contextPath/show/aId'\">"
       +"<a class=\"focus\" href=\"javascript:void(0)\">"
       +"<img class=\"thumb\" src=\"contextPathpicUrl\" style=\"display:inline;\"></a>"
       +"<header><a class=\"cat\" href=\"javascript:void(0)\">地理<i></i></a>"
       +"<h2><a class=\"dilitxt\" href=\"javascript:void(0)\">atitle</a></h2></header>"
       +"<p class=\"meta\"><time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i>start_time</time>"
	   +"</p></article>";
var renwu1="<article class=\"excerpt listright\" onclick=\"location.href='contextPath/show/aId'\">"
       +"<a class=\"focus\" href=\"javascript:void(0)\">"
       +"<img class=\"thumb\" src=\"contextPathpicUrl\" style=\"display:inline;\">"
	   +"</a><header><a class=\"cat\" href=\"javascript:void(0)\">世界<i></i></a>"
	   +"<h2><a class=\"dilitxt\" href=\"javascript:void(0)\">atitle</a></h2>"
	   +"</header><p class=\"meta\"><time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i>start_time</time>"
       +"</p></article>";
var renwu2="<article class=\"excerpt twohref\">"
       +"<div class=\"inline2div\"><a href='contextPath/show/aId1'>★&nbspatitle1</a>"
       +"<a href='contextPath/show/aId2'>★&nbspatitle2</a></div></article>";
var yeshi1="<article class=\"excerpt listtxt\" onclick=\"location.href='contextPath/show/aId'\">"
	   +"<header><a class=\"cat\" href=\"javascript:void(0)\">新加坡<i></i></a>"
	   +"<h2><a class=\"dilitxt\" href=\"javascript:void(0)\" >atitle</a></h2></header></article>";
var yeshi2="<article class=\"excerpt twohref\"><div class=\"inline2div\">"
	   +"<a href='contextPath/show/aId1'>&Xi;&nbspatitle1</a><a href='contextPath/show/aId2'>&Xi;&nbspatitle2</a></div></article>";
var yeshicrsl="<div class=\"item\"><a href=\"linkUrl\">"
       +"<img src=\"contextPathpicUrl\" class=\"img-responsive\" style=\"border-radius:0px;width:100%;height:100%;max-height: 100px;\"></a>"       
	   +"<a class=\"banner-title\" style=\"color: #fff;\" href=\"linkUrl\">image_desc</a></div>";        
	            



	




	
	
		
	


	
	


			
	
	

	
