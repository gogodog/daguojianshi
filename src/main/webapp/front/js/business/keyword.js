function searchByKeyword(page){
	keyword=$("input[name='keyword']").val();
	if('index'==page){
		currentPage=1;
		$("#pagination").nextAll().remove();
		loadData(keyword);
	}else{
		window.location.replace(contextPath+"/index?keyword="+keyword);
	}
};