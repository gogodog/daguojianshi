<!DOCTYPE html>
<html lang="en">

  <head>
    <title>30 Technology in 30 Days Timeline</title>
    <meta charset="utf-8">
    <meta name="description" content="30 Technology in 30 Days Timeline">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
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
    <!-- HTML5 shim, for IE6-8 support of HTML elements--><!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

    <script type="text/javascript" src="/front/timelinef/jquery-min.js"></script>
    <script type="text/javascript" src="/front/timelinef/storyjs-embed.js"></script>
    <script>
      $(document).ready(function() {
        $.get('/getstroies.json',function(result){
        	console.log(JSON.stringify(result));
            createStoryJS({
                type:   'timeline',
                width:    '100%',
                height:   '600',
                source:   result,
                embed_id: 'timeline',
                debug:    true
            });

        });
      });    
    </script>
  </head>
  <body><div id="timeline"></div></body>
</html>



