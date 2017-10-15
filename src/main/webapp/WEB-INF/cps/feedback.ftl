<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
        <#include "/cps/common/top.ftl">
        <#assign page_name='feedback'>
        <#include "/cps/common/menu.ftl">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">建议反馈</h1>
                        <h1 class="page-subhead-line">如果您有对cwillow.com平台或者cps.cwillow.com平台的建议，请您填写在下方发送给我们，我们会及时认真审核论证，认为方案可行后会做出相应的改变！如果方案被采纳，我们会联系您发送一份精美的小礼品，以此感谢你的建议</h1>
                    </div>
                </div>
                <div class="row">
	            <div class="col-md-6 col-sm-6 col-xs-12">
	               <div class="panel panel-info">
	                   <div class="panel-heading">&sect;正文</div>
	                   <div class="panel-body">
	                       <form role="form">
	                           <div class="form-group">
	                               <label>Hi，简史！</label>
	                               <textarea class="form-control" rows="4"></textarea>
	                           </div>
	                           <button type="button" class="btn btn-info">点击发送 >> 简史官方客服 </button>
	                       </form>
	                   </div>
	                   </div>
	               </div>
            	</div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
   <#include "/cps/common/f-static.ftl">
   <script>
      var contextPath = '${contextPath}';
      $("button[type='button']").click(function(){
    	  var judge_message = $(".form-control").val();
    	  if(judge_message == null || judge_message.length == 0){
    		  alert('反馈信息不能为空');
    		  return;
    	  }
    	  $.ajax({
    		   async:false,
    		   data:{judge_message:judge_message},
    		   dataType: "json",
    		   url:contextPath+"/cps/fb/saveFeedback",
    		   type:"POST",
    		   success:function(data) {
                   if(data.error){
                	    alert(data.errorMessage);
                   }else{
                    	alert('提交成功');
                   }
               }, 
               error:function(){
                   console.log("服务器繁忙...");
               }
           })
      })
   </script>
</body>
</html>
