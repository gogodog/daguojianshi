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