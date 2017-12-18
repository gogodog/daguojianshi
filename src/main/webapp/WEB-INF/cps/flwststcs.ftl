<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
        <#include "/cps/common/top.ftl">
        <#assign page_name='flwststcs/list'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">流量统计</h1>
                    </div>
                </div>
                <div id="container" style="min-width:100%;height:100%"></div>
                <div class="col-md-12">
					<h1 class="page-subhead-line"></h1>
                	<ul class="pager">
					  <li><a href="#" style="display:none" id="prevButn">上翻</a></li>
					  <li><a href="#" style="display:none" id="nextButn">下翻</a></li>
					</ul>
                </div>
             </div>
        </div>
    </div>
   <#include "/cps/common/f-static.ftl">
   <script src="/cps/js/hcharts/highcharts.js"></script>
   <script src="/cps/js/hcharts/exporting.js"></script>
   <script src="/cps/js/hcharts/highcharts-zh_CN.js"></script>
   <script>
      var contextPath = '${contextPath}';
      var currentPageNum = "1";
      
      $(function () {
    	  getVisits(currentPageNum);
      });

      function getVisits(currentPage){
      	$.ajax({
     		   async:false,
     		   data:{currentPage:currentPage},
     		   dataType:"json",
     		   url:contextPath+"/cps/flwststcs/getArticlescrapsVisits",
     		   type:"POST",
     		   success:function(data) {
                    if(data.error){
                  	  console.log(data.errorMessage);
                    }else{
                  	  if(data.titles!=null && data.titles.length>0){
                  		  var titles = data.titles;
                      	  var visits = data.visits;
                      	  var ids =  data.ids;
                          currentPageNum = data.currentPage;
                      	  var havePrev = data.havePrev;
                      	  var haveNext = data.haveNext;
                      	  havePrev? $("#prevButn").show() : $("#prevButn").hide();
                      	  haveNext? $("#nextButn").show() : $("#nextButn").hide();
                      	  visitsHcharts(titles,visits);
                  	  }
                    }
                }, 
                error:function(){
                    console.log("服务器繁忙...");
                }
           })
        }
      
       function visitsHcharts(titles,visits){
           $('#container').highcharts({
               chart: {
                   type: 'bar'
               },
               title: {
                   text: '文章访问量'
               },
               xAxis: {
                   categories: titles,
                   labels: {
                	   formatter: function() {
            	          return recursionSubStr(this.value);
                	   }
                   }
               },
               yAxis: {
                   min: 0,
                   title: {
                       text: '访问量'
                   },
                   allowDecimals:false
               },
               legend: {
                   reversed: true
               },
               plotOptions: {
                   series: {
                       stacking: 'normal',
                       events: {
                           click: function(event) {
            	              articleDayVisits(event);
                           }
                       }
                   }
               },
               series: [{
                   name: '访问量',
                   data: visits
               }]
           });
       }
       
       $("#prevButn").click(function(){
    	   getVisits(parseInt(currentPageNum)-1);
       })
       $("#nextButn").click(function(){
    	   getVisits(parseInt(currentPageNum)+1);
       })
       
       function articleDaysVisits(event){
    	   alert(event.point.category);
    	   $.ajax({
     		   async:false,
     		   data:{currentPage:currentPage},
     		   dataType:"json",
     		   url:contextPath+"/cps/flwststcs/getArticlescrapsVisits",
     		   type:"POST",
     		   success:function(data) {
                    if(data.error){
                  	  console.log(data.errorMessage);
                    }else{
                  	  if(data.titles!=null && data.titles.length>0){
                  		  var titles = data.titles;
                      	  var visits = data.visits;
                      	  var ids =  data.ids;
                          currentPageNum = data.currentPage;
                      	  var havePrev = data.havePrev;
                      	  var haveNext = data.haveNext;
                      	  havePrev? $("#prevButn").show() : $("#prevButn").hide();
                      	  haveNext? $("#nextButn").show() : $("#nextButn").hide();
                      	  visitsHcharts(titles,visits);
                  	  }
                    }
                }, 
                error:function(){
                    console.log("服务器繁忙...");
                }
           })
       }
       
       function recursionSubStr(str){
    	   var subStr='';
    	   var subLength=10;
    	   var index = 0;//
    	   if(str == '' || str == null){
    		   return '';
    	   }
    	   while(str.length > subLength){
    		   index++;//
    		   if(index==3){//
    			   subStr = subStr + '...';//
    			   return subStr;//
    		   }//
    		   subStr+=str.substr(0,subLength)+"<br>";
    		   str = str.substring(subLength,str.length-1)
    	   }
    	   return subStr+str;
       }
       
   </script>
</body>
</html>
