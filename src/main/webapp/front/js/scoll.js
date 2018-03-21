$(function() { 
	$(window).scroll(function() { 
	var top = $(window).scrollTop()+200; 
	var left= $(window).scrollLeft()+320; 
	$("#editInfo").css({top: top + "px" }); 
	}); 
});