<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
        <#include "/cps/common/top.ftl">
        <#assign page_name='wdoc'>
        <#include "/cps/common/menu.ftl">
        <input type="hidden" name="tag" value="${id}">
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12" style="float:none;">
                        <input name="title" class="form-control" style="font-size:24px;margin-bottom:10px;border: none;box-shadow: none;text-align: center;color: #555;" placeholder="内容标题" type="text" value="${(draft.title)!''}"/>
				        <input class="form-control" style="margin:3px" type="text" placeholder="作者" name="author" value="${(draft.author)!''}"/>
				        <input class="form-control" style="margin:3px" type="text" placeholder="关键词（多个请用#号隔开）" name="keywordsValue" value="${(draft.keywordsValue)!''}"/>
				        <label>类型:</label>
				        	<#list types as type>
						      <input style="margin-top:9px" type="radio" name="type" value="${type}" 
				                 <#if draft.type == type>checked</#if>
						      >${type.value}
						    </#list>
			        </div>
			        <div class="col-md-12" style="float:none;margin:2px;">
			          <label>文章内容起始时间:</label>
				      <div class="btn-group">
                        <button style="width:100%;" data-toggle="dropdown" class="btn btn-warning dropdown-toggle"><span id="start_time_c_show" >${draft.start_time_c!'公元'}</span><span class="caret"></span></button>
                        <ul class="dropdown-menu">
                        	<li><a href="#" tosl="公元" onmouseup="changeSelect(this,$('#start_time_c'),$('#start_time_c_show'))">公元</a></li>
                        	<li><a href="#" tosl="公元前" onmouseup="changeSelect(this,$('#start_time_c'),$('#start_time_c_show'))">公元前</a></li>
                        </ul>
                       </div>
                    <input type="hidden" value="${draft.start_time_c!'公元'}" id="start_time_c" name="start_time_c"/>
				      <input class="form-control" style="display: inline;width: 10%;" placeholder="年" type="text" name="start_time_y" value="${(draft.start_time_y)!''}" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'');if($('#start_time_c').val() == '公元'){if(this.value>new Date().getFullYear()||this.value<0){this.value='';return;}}else{if(this.value<0||this.value>=10000){this.value='';return;}}">
					  <input class="form-control" style="display: inline;width: 10%;" placeholder="月" type="text" name="start_time_m" value="${(draft.start_time_m)!''}" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'');if(this.value<=0||this.value>12){this.value=''}">
					  <input class="form-control" style="display: inline;width: 10%;" placeholder="日" type="text"name="start_time_d" value="${(draft.start_time_d)!''}" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'');if(this.value<=0||this.value>31){this.value=''}">
		            </div>
				 	<div class="col-md-12" style="float:none;margin:2px">
						<textarea name="sub_content" id="sub_content" class="form-control" style="margin-bottom:10px;text-indent:25px;" placeholder="内容摘要" rows="4">${(draft.sub_content)!''}</textarea>
					</div>
					<div class="col-md-12" style="float:none;">
                        <#include "/cps/common/editor.ftl">
                    </div>
                    <input type="hidden" name="aid" value="${draft.id}">
                </div>
    </div>
<#include "/cps/common/f-static.ftl">
<script>
function changeSelect(that,hidName,showName){
	hidName.val($(that).attr('tosl'));
	showName.text($(that).text());
}
</script>
</body>
</html>
