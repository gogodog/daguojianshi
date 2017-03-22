$(".page").find("a[link='first']").click(function(){
	$("input[name='currentPage']").val(1);
	$("#selectForm").submit();
});
$(".page").find("a[link='prev']").click(function(){
   	var currentPage=parseInt($("#current").html());
   	if(currentPage==1){
   		return;
   	}else{
   		$("input[name='currentPage']").val(currentPage-1);
   		$("#selectForm").submit();
   	}
});
$(".page").find("a[link='next']").click(function(){
	var total=parseInt($("#total").html());
	var currentPage=parseInt($("#current").html());
	if(total==currentPage){
		return;
	}else{
		$("input[name='currentPage']").val(currentPage+1);
		$("#selectForm").submit();
	}
});
$("#point").click(function(){
	var pageInput=parseInt($("#page-input").val());
	var total=parseInt($("#total").html());
	if(pageInput<1||pageInput>total){
		alert("请输入正确的页码");
		return;
	}
	$("input[name='currentPage']").val(pageInput);
	$("#selectForm").submit();
});
$("input[name='conditionButton']").click(function(){
	$("input[name='currentPage']").val(1);
	$("#selectForm").submit();
});
