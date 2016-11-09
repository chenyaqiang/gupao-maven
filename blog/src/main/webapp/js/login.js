function test() {
	var arr = [ '1', '2' ];
	$.ajax({
		type : "POST",
		url : "user/login.do",
		data : {
			'userName' : 'wy',
			'names' : arr
		},
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			alert(obj.msg)
		}
	});
}
(function($){
	var abc=function(){
		alert("abc");
	}
})(jQuery);