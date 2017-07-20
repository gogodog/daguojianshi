<button id="confirmAlertBtn" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#confirmAlert" style="display:none"></button>
<div class="modal fade" id="confirmAlert" tabindex="-1" role="dialog" aria-labelledby="confirmAlertTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="confirmAlertTitle"></h4>
            </div>
            <div class="modal-body" id="confirmAlertText"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
/*
 *调用方式:
 *引入：alert.ftl
 *调用方法：confirmAlert('测试标题','测试内容')
 */
function confirmAlert(title,text){
	$('#confirmAlertTitle').html(title);
	$('#confirmAlertText').html(text);
	$('#confirmAlertBtn').click();
}
</script>