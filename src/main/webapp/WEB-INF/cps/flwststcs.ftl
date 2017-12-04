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
                <div id="container" style="min-width:400px;height:400px"></div>
                <button type="button" class="btn btn-lg btn-primary" style="display:none" id="prevButn">《 </button>
                <button type="button" class="btn btn-lg btn-primary" style="display:none" id="nextButn"> 》</button>
             </div>
        </div>
    </div>
   <#include "/cps/common/f-static.ftl">
   <script src="${contextPath}/cps/js/hcharts/highcharts.js"></script>
   <script src="${contextPath}/cps/js/hcharts/exporting.js"></script>
   <script src="${contextPath}/cps/js/hcharts/highcharts-zh_CN.js"></script>
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
                   categories: titles
               },
               yAxis: {
                   min: 0,
                   title: {
                       text: '访问量'
                   }
               },
               legend: {
                   reversed: true
               },
               plotOptions: {
                   series: {
                       stacking: 'normal'
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
   </script>
</body>
</html>
