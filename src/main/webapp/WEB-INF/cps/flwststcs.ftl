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
             </div>
        </div>
    </div>
   <#include "/cps/common/f-static.ftl">
   <script src="${contextPath}/cps/js/hcharts/highcharts.js"></script>
   <script src="${contextPath}/cps/js/hcharts/exporting.js"></script>
   <script src="${contextPath}/cps/js/hcharts/highcharts-zh_CN.js"></script>
   <script>
      var contextPath = '${contextPath}';
      $(function () {
    	    $('#container').highcharts({
    	        chart: {
    	            type: 'bar'
    	        },
    	        title: {
    	            text: '堆叠条形图'
    	        },
    	        xAxis: {
    	            categories: ['苹果', '橘子', '梨', '葡萄', '香蕉']
    	        },
    	        yAxis: {
    	            min: 0,
    	            title: {
    	                text: '水果消费总量'
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
    	            name: '小张',
    	            data: [5, 3, 4, 7, 2]
    	        }, {
    	            name: '小彭',
    	            data: [2, 2, 3, 2, 1]
    	        }, {
    	            name: '小潘',
    	            data: [3, 4, 4, 2, 5]
    	        }]
    	    });
    	});

   </script>
</body>
</html>
