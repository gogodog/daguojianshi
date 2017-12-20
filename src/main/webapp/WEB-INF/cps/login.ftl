
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
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="login/assets/css/reset.css">
        <link rel="stylesheet" href="login/assets/css/supersized.css">
        <link rel="stylesheet" href="login/assets/css/style.css">
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>
<div class="login"><a href="javascript:void(0);" onclick="login()">微信登录</a></div>
<div id="title" style=" margin:  15% auto 0 auto;"><img src="/front/images/favico.ico">  <h1>简史网<f style="font-size:10px"> CPS</f></h1></div>
        <div class="page-container">
            <div class="form">
                <img id="ewm" src="/cps/img/login.png" style="display:none"/>
                <div class="connect" style="display:none">微信掃描二維碼登錄</div>
            </div>
        </div>

        <!-- Javascript -->
        <script src="login/assets/js/jquery-1.8.2.min.js"></script>
        <script src="login/assets/js/supersized.3.2.7.min.js"></script>
        <script src="login/assets/js/supersized-init.js"></script>
<script>
var isclose = true;
function login(){
$("#ewm").slideToggle("slow");
$(".connect").fadeToggle("slow");
if(isclose){
	isclose = false;
	$("#title").animate({'margin-top':'5%'});
}else{
	isclose = true;
	$("#title").animate({'margin-top':'15%'});
}
}
</script>
    </body>

</html>