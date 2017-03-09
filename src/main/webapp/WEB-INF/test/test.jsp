<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>测试</title>
</head>
<body>
	<h2>Test Url</h2>
	<h4 style="color: darkgreen;">result ==><div>${data} ${msg}</div></h4>
	<h2>Test Ajax</h2><input type="button" value="testAjax" onclick="testAjax()">
	<h4 style="color: darkgreen;">result ==> <div id="ajaxval" color="red"></div></h4>
	<h2>Test Ajax</h2>
	iinuuid:<input style="width: 300px;" id= "uuid" type="text" value="d0fe14dd818d45e89bf31c0cc41f86a5" readonly=readonly><br>
	submit:<input type="button" value="testDB" onclick="testDB()">
	<h4 style="color: darkgreen;">result ==> <div id="db" color="red"></div></h4>
</body>
<script>
	function getXMLHttpRequest() {
		var xhr;
		if (window.ActiveXObject) {
			xhr = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xhr = new XMLHttpRequest();
		} else {
			xhr = null;
		}
		return xhr;
	}
	function testAjax() {
		var xhr = getXMLHttpRequest();
		xhr.open("get", "/test/ajax?msg=ajax");
		//var data = "name=mikan&address=street...";
		//xhr.send(data);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("ajaxval").innerHTML += xhr.responseText+"<br>";
			}
		};
	}
	function testDB() {
		var xhr = getXMLHttpRequest();
		xhr.open("get", "/test/db?msg=d0fe14dd818d45e89bf31c0cc41f86a5");
		//var data = "name=mikan&address=street...";
		//xhr.send(data);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("db").innerHTML += xhr.responseText+"<br>";
			}
		};
	}
</script>
</html>