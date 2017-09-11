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
                    <!--
                    <div class="col-md-12">
                        <h1 class="page-head-line">编辑文章</h1>
                        <h1 class="page-subhead-line">旧时代里一个迂腐、寒碜的人，一个刚从乡下来到城市的人，甚至于“乍一看似长期吸毒（鸦片烟）的瘾君子”。 一 许广平在《鲁迅回忆录：手稿本》中，用了将近三大段文字描述鲁迅的外貌。 </h1>
                    </div> -->
                    <div class="col-md-12" style="float:none;">
                        <input name="title" class="form-control" style="font-size:24px;margin-bottom:10px;border: none;box-shadow: none;text-align: center;color: #555;" placeholder="内容标题" type="text" value="${(draft.title)!''}"/>
				        <input class="form-control" style="margin:3px" type="text" placeholder="作者" name="author" value="${(draft.author)!''}"/>
				        <input class="form-control" style="margin:3px" type="text" placeholder="关键词（多个请用#号隔开）" name="keywordsValue" value="${(draft.keywordsValue)!''}"/>
				        &nbsp;类型:
				        	<#list types as type>
						      <input style="margin-top:9px" type="radio" name="type" value="${type}" 
				                 <#if draft.type == type>checked</#if>
						      >${type.value}
						    </#list>
			        </div>
			        <div class="col-md-12" style="float:none;margin:2px">
			          &nbsp;文章内容起始时间:&nbsp;
			          <select name="start_time_c" class="form-select" style="width: 7%;">
					     <option value="公元" <#if draft.start_time_c == "公元">selected</#if>>公元</option>
					     <option value="公元前" <#if draft.start_time_c == "公元前">selected</#if>>公元前</option>
				      </select>
				      <input class="form-input-txt" style="width:5%;" type="text" name="start_time_y" value="${(draft.start_time_y)!''}" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')"/>年
				      <input class="form-input-txt" style="width:5%;" type="text" name="start_time_m" value="${(draft.start_time_m)!''}" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>月
				      <input class="form-input-txt" style="width:5%;" type="text" name="start_time_d" value="${(draft.start_time_d)!''}" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>日
		            </div>
				 	<div class="col-md-12" style="float:none;margin:2px">
						<textarea name="sub_content" id="sub_content" class="form-control" style="margin-bottom:10px" placeholder="内容摘要" rows="4">
						   ${(draft.sub_content)!''}
						</textarea>
					</div>
					<div class="col-md-12" style="float:none;">
                        <#include "/cps/common/editor.ftl">
                    </div>
                    <input type="hidden" name="aid" value="${draft.id}">
                </div>
    </div>
<#include "/cps/common/f-static.ftl">
</body>
</html>
