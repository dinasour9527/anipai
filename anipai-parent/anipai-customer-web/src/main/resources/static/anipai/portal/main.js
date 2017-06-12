$(function () {
    //head 弹出菜单部分
	initCategory();
	//initWaterFall();
});

function showAuction(thirdCategoryId) {
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/auction/beginAucList/' + thirdCategoryId,
		dataType: 'json',
		success: function(data) {
			initWaterFall(data);
		}
	});
}

function doAuc(auctionId) {
	$('.waterfall').html('');
	$('.auctionpage').load(parent.baseurl + '/auction/doAuction/' + auctionId);
}

function initWaterFall(data){
	$('.auctionpage').html('');
	html = '';
	$.each(data, function(index, obj){
		html = html + '<ul class="list-group">'
		 + '<li class="list-group-item">'
		 + '<img src="' + obj.path + '"/></li>'
		 + '<li class="list-group-item">'
		 + '<span>' + obj.auctionName + '</span>'
		 + '<button type="button" class="btn btn-default btn-xs pull-right" title="pin" onclick="doAuc(' + obj.auctionId + ')">'
		 + '<span class="fa fa-gavel">参拍</span></button></li>'
		 + '<li class="list-group-item">'
		 + '<p>' + obj.intro + '</p></li></ul>';
	});
	$('.waterfall').data('bootstrap-waterfall-template', html).waterfall();
}

function initCategory(){
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/category/menu',
		dataType: 'json',
		data: {},
		success: function(data) {
			$.each(data, function(index, obj){
				if(obj.level==1){
					$('#firstLevel').append(
						'<li style="border-top: none;">' +
							'<div class="cate-tag">' +
								'<strong><a href="javascript:;">' + obj.categoryName + '</a></strong>' +
							'</div>' +
							'<div class="list-item hide">' +
								'<ul id="secondLevel' + obj.categoryId + '" class="itemleft">' +
								'</ul>'+
							'</div>' +
						'</li>'
					);
				}
				if(obj.level==2){
					$('#secondLevel'+obj.parentCategoryId).append(
						'<dl>' +
							'<dt>' + obj.categoryName + '</dt>' +
							'<dd id="thirdLevel' + obj.categoryId + '">' +
							'</dd>' +
						'</dl>' +
						'<div class="fn-clear"></div>'
					);
				}
				if(obj.level==3){
					$('#thirdLevel'+obj.parentCategoryId).append(
						'<a href="#" onclick="showAuction(' + obj.categoryId + ');return false;">' + obj.categoryName + '</a>'
					);
				}
			});
			var cateLiNum = $(".cateMenu li").length;
	        $(".cateMenu li").each(function (index, element) {
	            if (index < cateLiNum) {
	                $(this).mouseenter(function () {
	                    var ty = $(this).offset().top - 158;
	                    var obj = $(this).find(".list-item");
	                    var sh = document.documentElement.scrollTop || document.body.scrollTop;
	                    var oy = ty + (obj.height() + 30) + 158 - sh;
	                    var dest = oy - $(window).height()
	                    if (oy > $(window).height()) {
	                        ty = ty - dest - 10;
	                    }
	                    if (ty < 0) ty = 0;
	                    $(this).addClass("on");
	                    obj.attr("style", "display: block!important;");
	                    $(".cateMenu li").find(".list-item").stop().animate({ "top": ty });
	                    obj.stop().animate({ "top": ty });
	                })
	                $(this).mouseleave(function () {
	                    $(this).removeClass("on");
	                    $(this).find(".list-item").attr("style", "display: none!important;");
	                })
	            }
	        });
	        $(".navCon_on").hover(function () {
	            $(".cateMenu").attr("style", "display: block!important;");
	        },
			function () {
			    $(".cateMenu").attr("style", "display: none!important;");
			})
		}
	});
}