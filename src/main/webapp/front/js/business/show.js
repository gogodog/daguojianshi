var currentPage = 1;
function judge(aid,level){
	jQuery.ajax({
        type:"POST",
        url: contextPath+"/judge",
        async: false,
        data: {"aid":aid,"level":level},
        dataType: "json",
        success:function(data) {
            if(data.error){
            	if(data.errorCode=="SERVICE_ERROR"){
            		alert("您已评价过哦");
            		$(".judge-tags a").removeAttr('onclick');
            	}
            }else{
            	alert("感谢您的评价");
            	$(".judge-tags a").removeAttr('onclick');
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

function getComments(){
	//获取文章id
	var thisURL = document.URL;
	var paths = thisURL.split("/");
	var aid = paths[paths.length-1];
	jQuery.ajax({
        type:"get",
        url: contextPath+"/getComments",
        async: false,
        data:{id:aid,currentPage:currentPage},
        dataType: "json",
        success:function(data) {
            if(data.error){
            }else{
            	var list = "";
            	var comments = data.objects;
            	for(var i=0;i<comments.length;i++){
            		var val = comments[i];
            		var commentsHtml="<li class=\"comment-content\"><span class=\"comment-f\">" +
        			                 "comment_index楼" +
        			                 "</span><div class=\"comment-main\"><p><a class=\"address\" href=\"javascript:void(0);\" rel=\"nofollow\">" +
        			                 "comment_name" +
        			                 "</a><span class=\"time\">" +
        			                 "(comment_time)" +
        			                 "</span><br>" +
        			                 "comment_comment" +
        			                 "</p></div></li>";
            		list += commentsHtml.replace(cre("comment_index"),parseInt(i+1))
            			.replace(cre("comment_name"),val.comment_name)
            			.replace(cre("comment_time"),val.formatComment_time)
            			.replace(cre("comment_comment"),val.comment);
            	}
                $("#comment_list").html(list);            	
            }
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

window.onload=function(){
	getComments();
}