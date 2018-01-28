window.LazyJoy = (function(window, document, undefined) {
	'use strict';
	var imgArys = [],
	offset,
	delayed,
	poll;
	var innerView = function(el) {
		var eyes = el.getBoundingClientRect();
		return ((eyes.top >= 0 && eyes.left >= 0 && eyes.top) <= (window.innerHeight || document.documentElement.clientHeight) + parseInt(offset));
	};
	var _pollImages = function() {
		for (var i = imgArys.length; i--;) {
			var self = imgArys[i];
			if (innerView(self)) {
				self.src = self.getAttribute('lazy-joy');
				imgArys.splice(i, 1);
				console.log(i);
			}
		}
	};
	var _delayed = function() {
		clearTimeout(poll);
		poll = setTimeout(_pollImages, delayed);
	};
	var getUp = function(obj) {
		var nodes = document.querySelectorAll('[lazy-joy]');
		var opts = obj || {};
		offset = opts.offset || 0;
		delayed = opts.delayed || 250;
		for (var i = 0; i < nodes.length; i++) {
			imgArys.push(nodes[i]);
		}
		_delayed();
		if (document.addEventListener) {
			window.addEventListener('scroll', _delayed, false);
		} else {
			window.attachEvent('onscroll', _delayed);
		}
	};
	return {
		getUp: getUp
	};
})(window, document);
/**
Model: <img src='默认图片.jpg' lazy-joy='真正显示图片'>
**/
