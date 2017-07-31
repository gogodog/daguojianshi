<!DOCTYPE html>
<html lang="en">

  <head>
    <#include "/front/common/header_static.ftl">
    <!-- Style-->
    <style>
      <style>
		html, body {
		    width: 100%;
		    height:100%;
		    padding: 0px;
		    margin: 0px;
		}
		#timeline-embed {
		    height: 100%;
		}
    </style>
    <script type="text/javascript" src="${contextPath}/front/timelinef/storyjs-embed.js?v=1"></script>
    <script>
      var contextPath='${contextPath}';
      var aid='${articlescrapId}';
      var isNext='${isNext}';
      $(document).ready(function() {
      	//front/timeline/data.json
      	//getstroies.json
        $.get(contextPath+'/getstroies.json?articlescrapId='+aid+"&isNext="+isNext,function(result){
//        	console.log(JSON.stringify(result));
        	if(result!=null&&result!=''&&result.maxTimeAid!=''&&result.maxTimeAid!=null&&result.minTimeAid!=null&&result.minTimeAid!=''){
        		var hidden ="<input type=\"hidden\" id=\"maxTimeAid\" value=\""+result.maxTimeAid+"\"><input type=\"hidden\" id=\"minTimeAid\" value=\""+result.minTimeAid+"\">";
            	$("#timeline").append(hidden);
        	}
        	var h = $(document).outerHeight(true) - 60 - 20 + 16;
            createStoryJS({
                type:   'timeline',
                width:    '100%',
                height:   h,
                source:   result,
                embed_id: 'timeline',
                debug:    false
            });
        });
      });
    </script>
  </head>
  <body>
  	<#assign page_name='timeline'>
  	<#include "/front/common/header.ftl">
  	<div id="timeline"></div>
  	<#include "/front/common/footer_static.ftl">
  </body>
</html>



