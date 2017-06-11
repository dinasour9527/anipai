$(function () {
    //head 弹出菜单部分
    var cateMenu = function () {
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
    								'<strong><a href="">' + obj.categoryName + '</a></strong>' +
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
    						'<a href="">' + obj.categoryName + '</a>'
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
    }();
});