var doubtLevel=1;
function getArticlescrapId(){
	//获取文章id
	var thisURL = document.URL;
	var paths = thisURL.split("/");
	var aid = paths[paths.length-1];
	return aid;
}

function judge(){
	var uname = $("input[name='uname']").val().trim();
	var email = $("input[name='email']").val().trim();
	var judge_message = $("#judge_message").val().trim();
	var levels = $("input[name='levels']").val().trim();
	var aid=getArticlescrapId().trim();
	if(uname==''){
		alert("请输入您的姓名");
		return;
	}
	if(levels!='DOUBT'){
		doubtLevel = null;
	}
	jQuery.ajax({
        type:"POST",
        url: contextPath+"/feedback/judge",
        async: false,
        data: {"articlescrap_id":aid,"judge_level":levels,"judge_message":judge_message,"uname":uname,"email":email,"doubt_level":doubtLevel},
        dataType: "json",
        success:function(data) {
            if(data.error){
            	if(data.errorCode=='SERVICE_ERROR'){
            		alert('您已经反馈过');
            	}else{
            		alert('服务器繁忙...');
            	}
            }else{
            	alert('填写成功');
            	window.location.href="http://www.cwillow.com/";
            }
        }, 
        error:function(){
            console.log("反馈失败");
        }
    });   
	
}

$("input[name='levels']").click(function(){
	if($(this).val()=='DOUBT'){
		$("[dataType='DOUBT']").show();
	}else{
		$("[dataType='DOUBT']").hide();
	}
});

$(".starRating>label").click(function(){
	doubtLevel=$(this).html();
});


