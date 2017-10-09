<form id="page" action="${contextPath}/cps/${page_name}" method="post" style="display:none">
	<input type="hidden" id="currentPage" name="currentPage" value="${pageinfo.currentPage}">
</form>
<script>
var totalPage='${pageinfo.totalPage}';
function spage(currentPage){
	if(currentPage <= 0){
		alert("没有那么近的地方了...");
		return;
	}
	if(currentPage > totalPage){
		alert("已经没有了远方的远方...");
		return;
	}
	$("#currentPage").val(currentPage);
	$("#page").submit();
}
</script>
<ul class="pagination">
	<li><a href="javascript:void(0);" onclick="spage(${pageinfo.currentPage-1})">&laquo;</a></li>
	<#list 1..pageinfo.totalPage as i>
		<li <#if i=pageinfo.currentPage>class="active"</#if>><a href="javascript:void(0);" onclick="spage(${i})">${i}</a></li>
	</#list>
	<li><a href="javascript:void(0);" onclick="spage(${pageinfo.currentPage+1})">&raquo;</a></li>
</ul>
<hr />
