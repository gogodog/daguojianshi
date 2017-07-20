<form id="page" action="/cps/${page_name}" method="post" style="display:none">
	<input type="hidden" id="curPage" name="curPage" value="${pageBean.curPage}">
</form>
<ul class="pagination">
	<li><a href="javascript:void(0);;" onclick="spage(${pageBean.curPage-1})">&laquo;</a></li>
	<#list 1..pageBean.pageCount as i>
		<li <#if i=pageBean.curPage>class="active"</#if>><a href="javascript:void(0);;" onclick="spage(${i})">${i}</a></li>
	</#list>
	<li><a href="javascript:void(0);;" onclick="spage(${pageBean.curPage+1})">&raquo;</a></li>
</ul>
<hr />
<script>
	function spage(curent){
		if(curent <= 0){
			alert("没有那么近的地方了...");
			return;
		}
		if(curent > ${pageBean.pageCount}){
			alert("已经没有了远方的远方...");
			return;
		}
		$("#curPage").val(curent);
		$("#page").submit();
	}
</script>