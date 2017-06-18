(function() {
    if (!window.pageinfo)
        return;
    var pageinfo = window.pageinfo;
    var ua = navigator.userAgent.toLowerCase();
    var pf = navigator.platform.toLowerCase();
    var ratio = typeof devicePixelRatio !== "undefined" ? devicePixelRatio : 1;
    var needDivideRatio = screen.width / document.body.clientWidth !== 1 && screen.height / document.body.clientHeight !== 1;
    var width = needDivideRatio ? screen.width / ratio : screen.width;
    var height = needDivideRatio ? screen.height / ratio : screen.height;
    var padpcmobile = function selectMachine(){
    	if(width >= 800){
    		return "pc";
    	}else if(width < 800 && width >= 500){
    		return "pad"
    	}else{
    		return "mobile";
    	}
    }
    var sendwindowsevent = "onload";
    var ip="";
    var mac="";
    var page=pageinfo.page;
    var pageid = pageinfo.pageid;
    var pagetype = pageinfo.pagetype;
    var pagedocids = pageinfo.pagedocids;
    var pageadids = pageinfo.pageadids;
    var channel="todo";
    var browseversion = function (){
    	    var userAgent = ua;
    	    var isOpera = userAgent.indexOf("opera") > -1;
    	    if (isOpera) {
    	        return "opera"
    	    }; 
    	    if (userAgent.indexOf("firefox") > -1) {
    	        return "firefox";
    	    }
    	    if (userAgent.indexOf("chrome") > -1){
    	    	return "chrome";
    	    }
    	    if (userAgent.indexOf("safari") > -1) {
    	        return "safari";
    	    }
    	    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("msie") > -1 && !isOpera) {
    	        return "ie";
    	    };
    	    return "other";
    }
    
    var os = function (){
    	    var sUserAgent = ua;
    	    var isWin = (pf == "win32") || (pf == "windows");
    	    var isMac = (pf == "mac68K") || (pf == "macppc") || (pf == "macintosh") || (pf == "macIntel");
    	    if (isMac) 
    	    	return "mac";
    	    var isUnix = (pf == "x11") && !isWin && !isMac;
    	    if (isUnix) 
    	    	return "unix";
    	    var isLinux = (String(pf).indexOf("linux") > -1);
    	    if (isLinux) 
    	    	return "linux";
    	    if (isWin) {
    	        var isWin2K = sUserAgent.indexOf("windows nt 5.0") > -1 || sUserAgent.indexOf("windows 2000") > -1;
    	        if (isWin2K) return "win2000";
    	        var isWinXP = sUserAgent.indexOf("windows nt 5.1") > -1 || sUserAgent.indexOf("windows xp") > -1;
    	        if (isWinXP) return "winxp";
    	        var isWin2003 = sUserAgent.indexOf("windows nt 5.2") > -1 || sUserAgent.indexOf("windows 2003") > -1;
    	        if (isWin2003) return "win2003";
    	        var isWinVista= sUserAgent.indexOf("windows nt 6.0") > -1 || sUserAgent.indexOf("windows vista") > -1;
    	        if (isWinVista) return "winvista";
    	        var isWin7 = sUserAgent.indexOf("windows nt 6.1") > -1 || sUserAgent.indexOf("windows 7") > -1;
    	        if (isWin7) return "win7";
    	        var isWin8 = sUserAgent.indexOf(sUserAgent.indexOf("windows 8") > -1);
    	        if (isWin8) return "win8";
    	        var isWin10 = sUserAgent.indexOf(sUserAgent.indexOf("windows 10") > -1);
    	        if (isWin10) return "win10";
    	    }
    	    return "other";
    }
    
    function ajaxgo(){
    	var result = {};
    	result['ua'] = ua;
    	result['width'] = Math.round(width);
    	result['height'] = Math.round(height);
    	result['padpcmobile'] = padpcmobile();
    	result['sendwindowsevent'] = sendwindowsevent;
    	result['ip'] = ip;
    	result['mac'] = mac;
    	result['page'] = page;
    	result['pageid'] = pageid;
    	result['pagetype'] = pagetype;
    	result['pagedocids'] = pagedocids;
    	result['pageadids'] = pageadids;
    	
    	result['channel'] = channel;
    	result['browseversion'] = browseversion();
    	result['os'] = os();
    	send(result);
    }
    var send = function(r){
    	ajax({ 
    		  type:"POST", 
    		  url:contextPath+"/data/dadian", 
    		  dataType:"json", 
    		  data:JSON.stringify(r),
    		  contentType:"application/json",
    		  success:function(result){ 
//    		      console.log(JSON.stringify(msg));
    			  //首页访问量
    			  var indexVisitCount = result.indexVisitCount;
    			  if(typeof(indexVisitCount) != "undefined" && indexVisitCount!=null){
    				  document.getElementById("sitetime").innerHTML=indexVisitCount;
	    		  }
    		  }
    		})
    }
	var ajax = function(){
		var ajaxData = {
			type : arguments[0].type || "GET",
			url : arguments[0].url || "",
			async : arguments[0].async || "true",
			data : arguments[0].data || null,
			dataType : arguments[0].dataType || "text",
			contentType : arguments[0].contentType
					|| "application/x-www-form-urlencoded",
			beforeSend : arguments[0].beforeSend || function() {
			},
			success : arguments[0].success || function() {
			},
			error : arguments[0].error || function() {
			}
		}
		ajaxData.beforeSend()
		var xhr = createxmlHttpRequest();
		xhr.responseType = ajaxData.dataType;
		xhr.open(ajaxData.type, ajaxData.url, ajaxData.async);
		xhr.setRequestHeader("Content-Type", ajaxData.contentType);
		xhr.send(convertData(ajaxData.data));
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					ajaxData.success(xhr.response)
				} else {
					ajaxData.error()
				}
			}
		}
	}
	var createxmlHttpRequest = function() {
		if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		}
	}
	var convertData = function(data) {
		if (typeof data === 'object') {
			var convertResult = "";
			for ( var c in data) {
				convertResult += c + "=" + data[c] + "&";
			}
			convertResult = convertResult
					.substring(0, convertResult.length - 1)
			return convertResult;
		} else {
			return data;
		}
	}
    ajaxgo();
})();
