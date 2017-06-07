$(function(){
	
});

function transFrame(url, obj) {
	var navNode = obj.parentNode.parentNode.previousSibling.previousSibling;
	var iconNode = navNode.childNodes[0];
	var breadcrumbs = '<li><i class="' + $(iconNode).attr('class') + '"></i><a>' + $(navNode).text() + '</a></li>'
		+'<li><a>' + $(obj).text() + '</a></li>';
	$('#head-crumbs').html(breadcrumbs);
	$('#content-main').attr('src', url);
	$(obj.parentNode.parentNode.childNodes).attr('class', '');
	$(obj.parentNode).attr('class', 'active-item');
}

function hasAuthority(authority) {
	var flag = false;
	$.each(currentUser.authorities, function(index, obj) {
		if(authority == obj.authority) {
			flag = true;
			return false;
		}
	})
    return flag;
}