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
      var aid='${timeline.articlescrapId}';
      var keyword='${timeline.keyword}';
      var type='${timeline.type}';
      var isNext='${isNext}';
      var isSlip='${isSlip}';
      var isContain='${isContain}';
      $(document).ready(function() {
    	  getData(contextPath+'/getstroies.json?articlescrapId='+aid+"&isNext="+isNext+"&isSlip="+isSlip+"&keyword="+keyword+"&type="+type+"&isContain="+isContain);
      });
      
      function getData(url){
    	  $.get(url,function(result){
          	if(result!=null&&result!=''&&result.maxTimeAid!=''&&result.maxTimeAid!=null&&result.minTimeAid!=null&&result.minTimeAid!=''){
          		var hidden ="<input type=\"hidden\" id=\"maxTimeAid\" value=\""+result.maxTimeAid+"\"><input type=\"hidden\" id=\"minTimeAid\" value=\""+result.minTimeAid+"\">";
          		hidden+="<input type=\"hidden\" id=\"timelinetype\" value=\""+type+"\"><input type=\"hidden\" id=\"timelinekeyword\" value=\""+keyword+"\">"
          		hidden+="<input type=\"hidden\" id=\"isHaveValue\" value=\""+result.isHaveValue+"\"><input type=\"hidden\" id=\"position\" value=\""+result.position+"\">"
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
      }
      
      function searchByKeyword(){
    	  var keyword = $("input[name='keyword']").val();
    	  window.location.href=contextPath+"/timeline?keyword="+keyword;
      }
    </script>
  </head>
  <body>
  	<#assign page_name='timeline'>
  	<#include "/front/common/timeline_header.ftl">
  	<div id="timeline"></div>
  	<#include "/front/common/footer_static.ftl">
  </body>
</html>



