<!DOCTYPE html>
<html>
<head>
<title>大国简史-反馈调查</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link href="${contextPath}/front/css/feedback.css" rel="stylesheet" type="text/css" media="all" />
<script>var contextPath='${contextPath}'</script>
</head>
<body>
<div class="content">
	<h1>大国简史-反馈调查</h1>
	<div class="main">
		<form>
				<input type="text" value="" maxlength="20" name="uname" placeholder="姓名" required="">
				<input type="text" value="" maxlength="50" name="email" placeholder="邮箱" required="">
		</form>	
		<h5>资料是否有问题？</h5>
			<div class="radio-btns">
					<div class="swit">		
					    <#list judgeLevels as level>
					        <div class="check_box"> <div class="radio"><label><input type="radio" name="levels" value="${level}" <#if level=='DOUBT'>checked</#if> ><i></i>${level.value}</label></div></div>
					    </#list>
						
						<div class="clear"></div>
					</div>
			</div>
		<h5 dataType="DOUBT">疑问程度?</h5>
			<span class="starRating" dataType="DOUBT">
			    <input id="rating5" type="radio" name="rating" value="5">
			    <label for="rating5">5</label>
			    <input id="rating4" type="radio" name="rating" value="4">
			    <label for="rating4">4</label>
			    <input id="rating3" type="radio" name="rating" value="3">
			    <label for="rating3">3</label>
			    <input id="rating2" type="radio" name="rating" value="2" checked="">
			    <label for="rating2">2</label>
			    <input id="rating1" type="radio" name="rating" value="1">
			    <label for="rating1">1</label>
			</span>
		<form>	
			<h5>相关问题备注</h5>	
				<textarea name="judge_message" id="judge_message" onkeyup="if(value.length>255) value=value.substr(0,255)" required=""></textarea>
				<input type="button" value="填写完毕" onclick="judge();">
		</form>
		<h5 style="font-size: smaller;color: #abb7b8;padding-top: 1em;">
			<p>感谢您对当代史资料库的支持！</p>
			<p>我们相信用户的反馈能让资料库日趋完善，在未来能让更多的用户以更有效的获取今天所真实发生的。</p>
		</h5>
	</div>
	<input type="hidden" id="doctype" value="${docid}">
</div>
<script src="${contextPath}/front/js/jquery-2.1.4.min.js"></script>
<script src="${contextPath}/front/js/business/feedback.js"></script>
</body>
</html>