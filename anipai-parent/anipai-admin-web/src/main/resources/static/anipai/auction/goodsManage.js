$(function(){
	var tb_goods = new Table();
	tb_goods.addEventListener();
	tb_goods.init();
	$('.firstcate_group').select2({
		theme: 'bootstrap',
		placeholder: '请选择一个分类',
		allowClear: true
	});
	$('#create_form_firstcid').on('select2:select', function(e) {
		$('#create_form_secondcid > option[value!=""]').remove();
		$('#create_form_thirdcid > option[value!=""]').remove();
		initSecondCategory(e.params.data.id, 'create', '');
	});
	$('#edit_form_firstcid').on('select2:select', function(e) {
		$('#edit_form_secondcid > option[value!=""]').remove();
		$('#edit_form_thirdcid > option[value!=""]').remove();
		initSecondCategory(e.params.data.id, 'edit', '');
	});
	$('#create_form_firstcid').on('select2:unselect', function(e) {
		$('#create_form_secondcid > option[value!=""]').remove();
		$('#create_form_thirdcid > option[value!=""]').remove();
	});
	$('#edit_form_firstcid').on('select2:unselect', function(e) {
		$('#edit_form_secondcid > option[value!=""]').remove();
		$('#edit_form_thirdcid > option[value!=""]').remove();
	});
	$('.secondcate_group').select2({
		theme: 'bootstrap',
		placeholder: '请选择一个分类',
		allowClear: true
	});
	$('#create_form_secondcid').on('select2:select', function(e) {
		$('#create_form_thirdcid > option[value!=""]').remove();
		initThirdCategory(e.params.data.id, 'create');
	});
	$('#edit_form_secondcid').on('select2:select', function(e) {
		$('#edit_form_thirdcid > option[value!=""]').remove();
		initThirdCategory(e.params.data.id, 'edit');
	});
	$('#create_form_secondcid').on('select2:unselect', function(e) {
		$('#create_form_thirdcid > option[value!=""]').remove();
	});
	$('#edit_form_secondcid').on('select2:unselect', function(e) {
		$('#edit_form_thirdcid > option[value!=""]').remove();
	});
	$('.thirdcate_group').select2({
		theme: 'bootstrap',
		placeholder: '请选择一个分类',
		allowClear: true
	});
	initFirstCategory();
	$('.modal').on('hide.bs.modal', function() {
		$('#animated-row').attr('class', 'row');
	});
	initPicUpload();
});

var Table = function() {
	var oTable = new Object();
	oTable.init = function() {
		$('#tb_goods').bootstrapTable({
			url: parent.baseurl + '/goods/goodsTable',             //请求后台的URL（*）
			method: 'get',                      //请求方式（*）
			toolbar: '#toolbar',                //工具按钮用哪个容器
			striped: true,                      //是否显示行间隔色
			cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination: true,                   //是否显示分页（*）
			sortable: true,                     //是否启用排序
			sortOrder: 'asc',                   //排序方式
			sortName: 'goodsId',
			//queryParams: oTableInit.queryParams,//传递参数（*）
			sidePagination: 'server',           //分页方式：client客户端分页，server服务端分页（*）
			pageNumber:1,                       //初始化加载第一页，默认第一页
			pageSize: 10,                       //每页的记录行数（*）
			pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
			//search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			//strictSearch: false,
			showColumns: true,                  //是否显示所有的列
			showRefresh: true,                  //是否显示刷新按钮
			//minimumCountColumns: 2,             //最少允许的列数
			clickToSelect: true,                //是否启用点击选中行
			height: 480,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId: 'goodsId',                     //每一行的唯一标识，一般为主键列
			showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
			cardView: false,                    //是否显示详细视图
	        detailView: false,                  //是否显示父子表
	        onLoadSuccess: function() {
	        	$('[data-toggle="tooltip"]').tooltip();
	        },
	        columns: [ {
	            field: 'goodsName',
	            title: '标的名称',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true,
	        }, {
	            field: 'firstCategoryName',
	            title: '一级分类',
	        	align: 'center',
	        	valign: 'middle'
	        }, {
	            field: 'secondCategoryName',
	            title: '二级分类',
	        	align: 'center',
	        	valign: 'middle'
	        }, {
	            field: 'thirdCategoryName',
	            title: '三级分类',
	        	align: 'center',
	        	valign: 'middle'
	        }, {
	            field: 'qtyTotal',
	            title: '总量(单位)',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true,
	        }, {
	        	field: 'action',
	        	title: '操作',
	        	align: 'center',
	        	valign: 'middle',
	        	formatter: function(value, row, index) {
	        		var btn_group = '<div class="btn-group btn-group-xs">';
	        			/*+ '<button class="btn btn-transparent"><i class="fa fa-eye"></i></button>'*/
	        		if(parent.hasAuthority('AUCTION_ADD') && row.qtyTotal-row.qtyAuction > 0) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="upAuction(' + row.goodsId + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="上拍">'
	        			+ 	'<i class="fa fa-gavel"></i></button>';
	        		}
	        		if(parent.hasAuthority('GOODS_EDIT')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="uploadPicModel(' + row.goodsId + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="图片上传">'
	        			+ 	'<i class="fa fa-upload"></i></button>';
	        		}
	        		if(parent.hasAuthority('GOODS_EDIT')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="editModel(' + row.goodsId + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="编辑">'
	        			+ 	'<i class="fa fa-pencil"></i></button>';
	        		}
	        		if(parent.hasAuthority('GOODS_DEL'))	{
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="confirmDeleteModel(' + row.goodsId + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="删除">'
	        			+ 	'<i class="fa fa-times"></i></button>'
	        			+ '</div>';
	        		}
	        		return btn_group;
	        	}
	    	} ]
		});
	};
	oTable.addEventListener = function() {
		$('#btn_add').click(function() {
			$('#create_form')[0].reset();
			$('#create_form .firstcate_group').val('').trigger('change');
			$('#create_form .secondcate_group').val('').trigger('change');
			$('#create_form .thirdcate_group').val('').trigger('change');
			$('#create_form_secondcid > option[value!=""]').remove();
			$('#create_form_thirdcid > option[value!=""]').remove();
			$('#create_modal').modal('show');
		});
		$('#create_form_deploy').click(function() {
			var param = $('#create_form').serialize();
			$.ajax({
				type: 'post',
				url: parent.baseurl + '/goods/goodsCreate',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#create_modal').modal("hide");
					$('#create_form')[0].reset();
					$('#tb_goods').bootstrapTable('refresh');
				}
			});
		});
		$('#edit_form_deploy').click(function() {
			var param = $('#edit_form').serialize();
			$.ajax({
				type: 'post',
				url: parent.baseurl + '/goods/goodsEdit',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#edit_modal').modal("hide");
					$('#edit_form')[0].reset();
					$('#tb_goods').bootstrapTable('refresh');
				}
			});
		});
		$('#delete_deploy').click(function() {
			var goodsId = $('#delete_deploy').data('goodsid');
			$.ajax({
				type: 'post',
				url: parent.baseurl + '/goods/goodsDelete',
				dataType: 'json',
				data: {
					'goodsId': goodsId
				},
				success: function(data) {
					$('#confirm_delete_model').modal("hide");
					$('#tb_goods').bootstrapTable('refresh');
				}
			});
		});
	};
	return oTable;
}

function initFirstCategory() {
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/category/listByLevel/1',
		dataType: 'json',
		success: function(data) {
			$.each(data, function(index, obj) {
				var option = '<option value="' + obj.categoryId + '">'
							 + obj.categoryName
							 + '</option>'
				$('.firstcate_group').append(option);
			});
		}
	});
}

function initSecondCategory(parentCategoryId, formType, categoryId) {
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/category/listByParentCategoryId/' + parentCategoryId,
		dataType: 'json',
		success: function(data) {
			$.each(data, function(index, obj) {
				var option = '<option value="' + obj.categoryId + '">'
							 + obj.categoryName
							 + '</option>'
				$('#' + formType + '_form_secondcid').append(option);
			});
			$('#' + formType + '_form_secondcid').val(categoryId).trigger('change');
		}
	});
}

function initThirdCategory(parentCategoryId, formType, categoryId) {
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/category/listByParentCategoryId/' + parentCategoryId,
		dataType: 'json',
		success: function(data) {
			$.each(data, function(index, obj) {
				var option = '<option value="' + obj.categoryId + '">'
							 + obj.categoryName
							 + '</option>'
				$('#' + formType + '_form_thirdcid').append(option);
			});
			$('#' + formType + '_form_thirdcid').val(categoryId).trigger('change');
		}
	});
}

function confirmDeleteModel(goodsId) {
	$('#delete_deploy').data('goodsid', goodsId);
	$('#confirm_delete_model').modal('show');
}

function editModel(goodsId) {
	$('#edit_form')[0].reset();
	$('#edit_form_secondcid').val('').trigger('change');
	$('#edit_form_thirdcid').val('').trigger('change');
	$('#edit_form_secondcid > option[value!=""]').remove();
	$('#edit_form_thirdcid > option[value!=""]').remove();
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/goods/goodsEditForm/' + goodsId,
		dataType: 'json',
		success: function(data) {
			$('#edit_form_goodsId').val(data.goodsId);
			$('#edit_form_goodsName').val(data.goodsName);
			$('#edit_form_firstcid').val(data.firstCategoryId).trigger('change');
			initSecondCategory(data.firstCategoryId, "edit", data.secondCategoryId);
			initThirdCategory(data.secondCategoryId, "edit", data.thirdCategoryId);
			$('#edit_form_assessPrice').val(data.assessPrice);
			$('#edit_form_expectPrice').val(data.expectPrice);
			$('#edit_form_unit').val(data.unit);
			$('#edit_form_qtyTotal').val(data.qtyTotal);
		}
	});
	$('#edit_modal').modal('show');
}

function upAuction(goodsId) {
	parent.transFrame(parent.baseurl + '/auction/auctionManage/'+goodsId, parent.$('#auctionManage')[0]);
}

function uploadPicModel(goodsId) {
	$('#form_pic_upload').data('goodsid', goodsId);
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/file/getPic/' + goodsId,
		dataType: 'json',
		success: function(data) {
			$('#pic_show').attr('src',parent.baseurl + data.path);
			$('#pic_progress').css('width','0%');  
	        $('#pic_progress').html('0%');
			$('#pic_upload').css({display:'none'});
	        $('#pic_cancle').css({display:''});
			$('#pic_upload_model').modal('show');
		},
		error: function(data) {
			$('#pic_show').attr('src',parent.baseurl + '/anipai/auction/picplaceholder.png');
			$('#pic_progress').css('width','0%');  
	        $('#pic_progress').html('0%');
			$('#pic_upload').css({display:''});
	        $('#pic_cancle').css({display:'none'});
	        $('#pic_upload_model').modal('show');
		}
	});
}

function deleteUploadPic() {
	var goodsId = $('#form_pic_upload').data('goodsid');
	$.ajax({
		type: 'post',
		url: parent.baseurl + '/file/deletePic/' + goodsId,
		dataType: 'json',
		success: function(data) {
			$('#pic_show').attr('src',parent.baseurl + '/anipai/auction/picplaceholder.png');
			$('#pic_upload').css({display:''});
	        $('#pic_cancle').css({display:'none'});
		}
	});
}

function initPicUpload() {
	$("#form_pic_upload").fileupload({  
        url: parent.baseurl + '/file/goodsPicUpload',
        sequentialUploads: true,
        //maxFileSize: 200,
        formData: function (form) {
        	return [
        		{name:'goodsId',value:$('#form_pic_upload').data('goodsid')}
        	];
        }
    }).bind('fileuploadprogress', function (e, data) {  
        var progress = parseInt(data.loaded / data.total * 100, 10);  
        $('#pic_progress').css('width',progress + '%');  
        $('#pic_progress').html(progress + '%');  
    }).bind('fileuploaddone', function (e, data) {  
        $('#pic_show').attr('src',parent.baseurl + '/goodsimg/goodsimg'+$('#form_pic_upload').data('goodsid')+'.jpg');  
        $('#pic_upload').css({display:'none'});  
        $('#pic_cancle').css({display:''});  
    });
}