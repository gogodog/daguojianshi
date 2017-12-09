var currentPage = 1;
var floor = 0;

function cre(str){
	return new RegExp(str,"gm");
}

function getArticlescrapId(){
	//获取文章id
	var thisURL = document.URL;
	var paths = thisURL.split("/");
	var aid = paths[paths.length-1];
	return aid;
}

function getComments(){
	var aid=getArticlescrapId();
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
            		var val=comments[i];
            		list += commentsHtml(val.comment_name,val.formatComment_time,val.comment,++floor);
            	}
            	$('#comment_list').append(list);
                currentPage++;
            }
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

function commentsHtml(comment_name,formatComment_time,comment,floor){
	var commentsHtml="<li class=\"comment-content\"><div class=\"comment-main\"><p><a class=\"address\" href=\"javascript:void(0);\" rel=\"nofollow\">" +
                     "comment_name" +
                     "</a><span class=\"time\">" +
                     "(comment_time)" +
                     "</span><br>" +
                     "comment_comment" +
                     "</p></div></li>";
	commentsHtml=commentsHtml.replace(cre("comment_name"),comment_name)
	                .replace(cre("comment_time"),formatComment_time)
	                .replace(cre("comment_comment"),comment);
	 return commentsHtml;
}


window.onload=function(){
	getComments();
}

$(window).scroll(function(){
	totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
    if ($(document).height() <= totalheight) {
    	getComments();
    }
});

function saveComments(){
	var comment_name=$("input[name='comment_name']").val();
	var email=$("input[name='email']").val();
	var comment=$("#comment-textarea").val();
	var aid = getArticlescrapId();
	jQuery.ajax({
        type:"POST",
        url: contextPath+"/saveComments",
        async: false,
        data: {"comment_name":comment_name,"email":email,"comment":comment,"articlescrap_id":aid},
        dataType: "json",
        success:function(data) {
            if(data.error){
            	alert("服务器繁忙...");
            }else{
            	$('#comment_list').prepend(commentsHtml(comment_name,data.objects,comment,0));
            }
        }, 
        error:function(){
            console.log("加载失败");
        }
    });   
}

function timer(time) {
    var btn = $("#comment-submit");
    btn.attr("disabled", true);  //按钮禁止点击
    btn.html(time <= 0 ? "评论" : ("" + (time) + "秒后再评"));
    var hander = setInterval(function() {
        if (time <= 0) {
            clearInterval(hander); //清除倒计时
            btn.html("评论");
            btn.attr("disabled", false);
            return false;
        }else {
            btn.html("" + (time--) + "秒后再评");
        }
    }, 1000);
}


$("#comment-submit").click(function(){
	 var comment_name = $('#comment_name').val();
	 var comment = $('#comment-textarea').val();
	 var email=$("input[name='email']").val();
	 if(comment_name == null || comment_name == ''){
		 alert('请输入您的昵称');
		 return;
	 }
	 if(comment_name.length>30){
		 alert('昵称长度不能超过30');
		 return;
	 }
	 if(email.length>50){
		 alert('邮箱长度不能超过50');
		 return;
	 }
	 if($.trim(email)){
		 if(!isEmail(email)){
			 alert('邮箱格式错误');
			 return;
		 }
	 }
	 if(comment == null || comment == ''){
		 alert('请输入评论内容');
		 return;
	 }
	 if(comment.length>255){
		 alert('评论内容不能超过1000');
		 return;
	 }
	 timer(60);
	 $('#respond').toggle(500);
	 saveComments();
});
function isEmail(str){ 
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
	return reg.test(str); 
} 