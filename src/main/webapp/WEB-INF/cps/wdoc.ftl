<#include "/cps/common/header.ftl">
<body>
    <div id="wrapper">
        <#include "/cps/common/top.ftl">
        <#assign page_name='wdoc'>
        <#include "/cps/common/menu.ftl">
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">编辑文章</h1>
                        <h1 class="page-subhead-line">旧时代里一个迂腐、寒碜的人，一个刚从乡下来到城市的人，甚至于“乍一看似长期吸毒（鸦片烟）的瘾君子”。 一 许广平在《鲁迅回忆录：手稿本》中，用了将近三大段文字描述鲁迅的外貌。 </h1>

                    </div>
                    <form role="form">
					<div class="col-md-5" style="float:none;">
						<input class="form-control" placeholder="内容标题" type="text" />
					</div>
					<div class="col-md-5" style="float:none;">
						<textarea class="form-control" placeholder="内容摘要" rows="4"></textarea>
					</div>
					<div class="col-md-5" style="float:none;">
			<fieldset style="border: solid 1px #ccc;border-radius: 4px;border-radius: 4px;padding: inherit;">
				<legend style="font-size:14px;margin:0;width:initial;border-bottom:0">类别</legend>
				<label class="rlabel" for="radio-5" tabindex="1">项目 1</label>
				<input type="radio" name="radio-button-inline" id="radio-5" value="radio-1" />
	
				<label class="rlabel" for="radio-6" tabindex="2">项目 2</label>
				<input type="radio" name="radio-button-inline" id="radio-6" value="radio-2" />
	
				<label class="rlabel" for="radio-7" tabindex="3">项目 3</label>
	
				<input type="radio" name="radio-button-inline" id="radio-7" value="radio-3" />
	
				<label class="rlabel" for="radio-8" tabindex="4">项目 4</label>
				<input type="radio" name="radio-button-inline" id="radio-8" value="radio-4" />
			</fieldset>
					</div>
                    <div class="col-md-12">
                        <#include "/cps/common/editor.ftl">
                    </div>
                    </form>
                </div>
    </div>
<#include "/cps/common/f-static.ftl">
</body>
</html>
