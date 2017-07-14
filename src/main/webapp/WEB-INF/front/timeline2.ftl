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
    <script type="text/javascript" src="/front/timelinef/storyjs-embed.js"></script>
    <script>
      $(document).ready(function() {
      	//front/timeline/data.json
      	//getstroies.json
        $.get('/getstroies.json',function(result){
        	console.log(JSON.stringify(result));
        	var h = $(document).outerHeight(true) - 60 - 20;
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



