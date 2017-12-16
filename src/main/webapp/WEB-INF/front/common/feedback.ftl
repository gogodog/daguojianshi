<!DOCTYPE html>
<html>
<head>
<title>大国简史-反馈调查</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link href="/front/css/feedback.css" rel="stylesheet" type="text/css" media="all" />
<script>
    //页面统计
    var pageinfo_ = {};
    pageinfo_['page'] = 'feedback';
    pageinfo_['pagetype'] = 'other';//list or detail or other
    pageinfo_['pagedocids'] = '${pagedocids}';//文章id
    pageinfo_['pageadids'] = "${(pageadids)!''}";//广告id
    pageinfo_['pageid'] = '${pageid}';
    window['pageinfo'] = pageinfo_;
    //
    var contextPath='${contextPath}';
    var keyword="${(keyword)!''}";
	function GetQueryString(name){
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return decodeURI(r[2]);return "无";
	}
	function setTitle(){
		document.getElementById('tittsle').innerHTML = GetQueryString("title");
	}
</script>
</head>
<body onload="setTitle()">
<div class="content">
	<h1>大国简史-反馈调查</h1>
	<div class="main">
		<h5 dataType="DOUBT" style="">资料名称:<a class="notetag" id="tittsle">2星(感觉疑惑)5星(拥有部分证据)</a></h5>
		<form>
				<input type="text" value="" maxlength="20" name="uname" placeholder="姓名" required="">
				<input type="text" value="" maxlength="50" name="email" placeholder="邮箱" required="">
		</form>	
		<h5>资料是否有问题？</h5>
			<div class="radio-btns">
					<div class="swit">		
					    <#list judgeLevels as level>
					        <div class="check_box"> <div class="radio"><label><input type="radio" name="levels" value="${level}" <#if level=='DOUBT'>checked</#if>><i></i>${level.value}</label></div></div>
					    </#list>
						<div class="clear"></div>
					</div>
			</div>
		<h5 dataType="DOUBT">疑问程度?<a class="notetag">2星(感觉疑惑)5星(拥有部分证据)</a></h5>
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
			<p><a href="javascript:void(0);" onclick="history.go(-1)">返回文章&gt;&gt;&gt;</a></p>
		</h5>
	</div>
	<input type="hidden" id="doctype" value="${docid}">
</div>
<script src="/front/js/jquery-2.1.4.min.js"></script>
<script src="/front/js/business/feedback.js"></script>
<script src="/front/js/business/dadian.js"></script>
</body>
</html>