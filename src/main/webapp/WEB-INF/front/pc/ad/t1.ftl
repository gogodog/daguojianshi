<#if 1=1>
<div class="t1" style="display:none">
<a href="javascript:void(0);" onclick="hidet1()">关闭</a>
<img src="http://img4.imgtn.bdimg.com/it/u=4220205916,2193830798&amp;fm=214&amp;gp=0.jpg">
</div>
<style>
.t1{
	text-align: center;
	/*background-color:#000000;*/
	background:rgba(255,00,255,0.2);
	position: absolute;
	width: 100%;
	z-index: 1000;
	/*border: solid 3px black;*/
}
.t1 a{
	position: absolute;
	right: 40px;
	bottom: 10px;
	color: aliceblue;
}
.t1 img{

}
</style>
<script>
	function hidet1(){
		$('.t1').slideUp(1000);
	}
	function showt1(){
		$('.t1').slideDown(3000);
	}
	showt1();
	setTimeout(hidet1,5000);
</script>
</#if>