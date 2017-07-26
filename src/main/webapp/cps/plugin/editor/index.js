var editor;
$(function (){
    var E = window.wangEditor
    editor = new E('#divDemo')
    editor.customConfig.uploadImgShowBase64 = true
    editor.create()
});
function getHtml(){
	return editor.txt.html();
}