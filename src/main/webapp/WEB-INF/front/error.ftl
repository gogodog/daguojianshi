<!doctype html>
<html lang="zh-CN">
<head>
    <title>大国简史</title>
    <#include "common/header_static.ftl">
    <style type="text/css">
        .panel
        {
            padding: 80px 20px 0px;
            min-height: 400px;
            cursor: default;
        }
        .text-center
        {
            margin: 0 auto;
            text-align: center;
            border-radius: 10px;
            max-width: 900px;
            -moz-box-shadow: 0px 0px 5px rgba(0,0,0,.3);
            -webkit-box-shadow: 0px 0px 5px rgba(0,0,0,.3);
            box-shadow: 0px 0px 5px rgba(0,0,0,.1);
        }
        .float-left
        {
            float: left !important;
        }
        .float-right
        {
            float: right !important;
        }
        img
        {
            border: 0;
            vertical-align: bottom;
        }
        h2
        {
            padding-top: 20px;
            font-size: 20px;
        }
        .padding-big
        {
            padding: 20px;
        }
        .alert
        {
            border-radius: 5px;
            padding: 15px;
            border: solid 1px #ddd;
            background-color: #f5f5f5;
        }
    </style>
</head>
<body class="user-select">
    <section class="container">
	  <div class="panel">
	    <div class="text-center">
	      <h2><stong>${info.errorCode}:${info.tips}</stong></h2>
	        <div class="padding-big"> <a href="${info.callback}" class="btn btn-primary" >${info.callbakcbtn}</a>
	        </div>
	    </div>
	  </div>
	</section>
</body>
</html>
