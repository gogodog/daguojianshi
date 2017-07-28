var totalheight = 0;
var currentPage = 1;
var isonload = true;
function loadData(keyword){   
	    var url = contextPath+"/list?currentpage="+currentPage+"&type="+$("#doctype").val();
	    if(keyword!=null&&keyword!=''){
	    	url+="&keyword="+keyword;
	    }
        jQuery.ajax({
            type:"POST",
            url: url,async: false,
            dataType: "json",
            success:function(data) {
                var ctntary = eval(data.pageInfo.objects);
                if(ctntary && ctntary.length > 0){
                	appendCtntTmp(ctntary,data.imageContextPath,data.visits,data.isTypeShow);
                }
            }, 
            error:function(){
                console.log("加载失败");
            }
        });   
}
function cre(str){
	return new RegExp(str,"gm");
}
function appendCtntTmp(ctntary,imageContextPath,visits,isTypeShow){
	var list = "";
	var typeShowHtml="";
	if(!isTypeShow){
		typeShowHtml="style=\"display:none\"";
	}
	for(var i=0;i<ctntary.length;i++){
		var val = ctntary[i];
		var ctntTmp = "<article class='excerpt excerpt-1' onclick='location.href=\"contextPath/show/articlescrap_id\"'><a class='focus' href='contextPath/show/articlescrap_id' title='articlescrap_title'>"+
					  "<img class='thumb' data-original='contextPath/front/images/list/timg3.jpeg' src='imageContextPatharticlescrap_show_picture' style='display:inline;'>"+
					  "</a><header><a class='cat' href='javascript:void(0)' isTypeShow title='articlescrap_type_value'>articlescrap_type_value<i></i></a><h2>"+
					  "<a href='contextPath/show/articlescrap_id' title='articlescrap_title'>articlescrap_title</a></h2></header><p class='meta'>"+
					  "<time class='time'><i class='glyphicon glyphicon-time'></i> articlescrap_start_time</time><span class='views' id='new_visit_articlescrap_id'>"+
					  "<i class='glyphicon glyphicon-eye-open'></i> visits</span></p><p class='note'>articlescrap_sub_content</p></article>";
		list += ctntTmp.replace(cre("articlescrap_id"),val.id)
			.replace(cre("contextPath"),contextPath)
			.replace(cre("articlescrap_title"),val.title)
			.replace(cre("articlescrap_show_picture"),val.pictures[0])
			.replace(cre("articlescrap_type_value"),val.typeValue)
			.replace(cre("articlescrap_start_time"),val.start_time)
			.replace(cre("imageContextPath"),imageContextPath)
			.replace(cre("articlescrap_sub_content"),val.sub_content)
			.replace(cre("isTypeShow"),typeShowHtml)
			.replace(cre("visits"),visits[val.id]);
	}
	$('#content_t').append(list);
	currentPage++;
}
$(window).scroll(function(){
	totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
    if ($(document).height() <= totalheight) {
       loadData(keyword);
    }
});
window.onload=function(){
	if(keyword!=''&&keyword!=null){
		$("input[name='keyword']").val(keyword);
		searchByKeyword('index');
	}else{
		loadData(keyword);
	}
	channel();
};

function channel(){
	jQuery.ajax({
        type:"get",
        url: contextPath+"/channelList",
//        async: false,
        dataType: "json",
        success:function(data) {
           var channelList = data.channelList;
           var caList = data.ca;
           var li="";
           var div="";
           for(var i=0;i<channelList.length;i++){
        	   if(i==0){
        		   li+="<li role=\"presentation\" onclick=\"getContent(this,"+i+");\" class=\"active\"><a href=\"#notice\" aria-controls=\"notice\" role=\"tab\" data-toggle=\"tab\" draggable=\"false\">"+channelList[i].c_name+"</a></li>";
        		   div+="<div role=\"tabpanel\" class=\"tab-pane contact active\" id=\"notice\">";
        	   }else{
        		   li+="<li role=\"presentation\" onclick=\"getContent(this,"+i+");\"><a href=\"#contact\" aria-controls=\"contact\" role=\"tab\" data-toggle=\"tab\" draggable=\"false\">"+channelList[i].c_name+"</a></li>";
        		   div+= "<div role=\"tabpanel\" class=\"tab-pane contact\" id=\"contact\" style=\"display:none\">";
        	   }
        	   var ca=caList[channelList[i].id];
        	   if(ca!=null){
        		   for(var j=0;j<ca.length;j++){
        			  div+= "<h2><span><a href=\""+contextPath+"/show/"+ca[j].channelArticlescrap.articlescrap_id+"\">"+ca[j].title+"</a></span></h2>";
            	   }
        	   }
        	   div+="</div>"
           }
           $("#tablist").html(li);
           $(".tab-content-1").html(div);
           $("#tablist").parent().show();
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

function getContent(item,index){
	var div=$(item).parent().next("div");
	for(var i=0;i<$(div).children("div").length;i++){
		$(div).children("div:eq("+i+")").hide();
	}
	$(div).children("div:eq("+index+")").show();
}
