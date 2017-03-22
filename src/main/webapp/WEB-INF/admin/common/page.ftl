<div class="page">
 	<a href="javascript:void(0)" link="first">首页</a>
 	<a href="javascript:void(0)" link="prev">上一页</a>
 	<a href="javascript:void(0)" link="next">下一页</a>
 	第<span style="color:red;font-weight:600" id="current">${(pageInfo.currentPage)!'1'}</span>页
 	共<span style="color:red;font-weight:600" id="total">${(pageInfo.totalPage)!'1'}</span>页
 	<input type="text" class="page-input" id="page-input" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${(pageInfo.currentPage)!'1'}">
 	<input type="submit" class="page-btn" value="跳转" id="point">
</div>