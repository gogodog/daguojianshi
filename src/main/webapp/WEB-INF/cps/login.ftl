
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	<meta charset="utf-8">
	<title>简史网-微信登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="apple-touch-icon-precomposed" href="/front/images/favico.ico">
	<link rel="shortcut icon" href="/front/images/favico.ico">
	<link rel='stylesheet' href='//fonts.googleapis.com/css?family=PT+Sans:400,700'>
	<link rel="stylesheet" href="/cps/login/assets/css/reset.css">
	<link rel="stylesheet" href="/cps/login/assets/css/supersized.css">
	<link rel="stylesheet" href="/cps/login/assets/css/style.css">
	<!--[if lt IE 9]>
	<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>
<div class="login"><a href="javascript:void(0);" onclick="login()">微信登录</a></div>
<div id="title" style=" margin:  15% auto 0 auto;">
	<img src="/front/images/favico.ico">
	<h1>简史网<f style="font-size:10px"> CPS</f></h1>
</div>
<div class="page-container">
    <div class="form" id="login_container"></div>
</div>
<!-- Javascript -->
<script src="/cps/login/assets/js/jquery-1.8.2.min.js"></script>
<script src="/cps/login/assets/js/supersized.3.2.7.min.js"></script>
<script src="/cps/login/assets/js/supersized-init.js"></script>
<script src="//res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
<script>
var obj = new WxLogin({
	  id:"login_container", 
	  appid: "wxc146262e5d8a95cc", 
	  scope: "snsapi_login", 
	  redirect_uri: "https%3a%2f%2fwww.cwillow.com%2fcps%2fck",
	  state: "19002",
	  style: "white",
	  href: ""
});
</script>
</body>
</html>