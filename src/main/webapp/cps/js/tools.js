/**
 * cps前端工具类 author:cott date:2017-10-30 disc:set tool's function to Jquery ,when
 * you useing that by Jquery ==> $.desc
 * 
 * LimitTextArea:limit element's length about inputing.
 * 
 */
function syTools(msg) {
	alert(msg);
}
/**
 * @param field:当前对象
 * @param max:内容最大长度
 */
function limitTextArea(that, maxLeg) {
	if (that.value.length > maxLeg) {
		that.value = that.value.substring(0, maxLeg);
	}
}
