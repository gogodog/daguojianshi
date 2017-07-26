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
                    <div class="col-md-12">
                        <h1 class="page-head-line">编辑文章</h1>
                        <h1 class="page-subhead-line">旧时代里一个迂腐、寒碜的人，一个刚从乡下来到城市的人，甚至于“乍一看似长期吸毒（鸦片烟）的瘾君子”。 一 许广平在《鲁迅回忆录：手稿本》中，用了将近三大段文字描述鲁迅的外貌。 </h1>
                    </div>
                    <form role="form">
					<div class="col-md-12" style="float:none;">
						<input id="title" class="form-control" style="font-size:24px;margin-bottom:10px;border: none;box-shadow: none;text-align: center;color: #555;" placeholder="内容标题" type="text" />
						<textarea id="sub" class="form-control" style="margin-bottom:10px" placeholder="内容摘要" rows="4"></textarea>
					</div>
					<div class="col-md-12" style="float:none;">
                        <#include "/cps/common/editor.ftl">
                    </div>
                    </form>
                </div>
    </div>
<#include "/cps/common/f-static.ftl">
</body>
</html>
