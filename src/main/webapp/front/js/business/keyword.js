function searchByKeyword(page){
	keyword=$("input[name='keyword']").val();
	window.location.replace(contextPath+"/timeline?keyword="+keyword);
};