$(function(){
	var tb_category1 = new Table(1);
	tb_category1.addEventListener();
	tb_category1.init();
	
	var tb_category2 = new Table(2);
	tb_category2.addEventListener();
	tb_category2.init();
	
	var tb_category3 = new Table(3);
	tb_category3.addEventListener();
	tb_category3.init();
	
	$('.modal').on('hide.bs.modal', function() {
		$('#animated-row').attr('class', 'row');
	});
});

var Table = function(level) {
	var oTable = new Object();
	oTable.init = function() {
		$('#tb_category'+level).bootstrapTable({
			url: '/category/categoryTable',     //请求后台的URL（*）
			method: 'get',                      //请求方式（*）
			toolbar: '#toolbar'+level,          //工具按钮用哪个容器
			striped: true,                      //是否显示行间隔色
			cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination: true,                   //是否显示分页（*）
			sortable: true,                     //是否启用排序
			sortOrder: 'asc',                   //排序方式
			sortName: 'categoryId',
			queryParams: function(params){
				return {
					sort: params.sort,
					order: params.order,
					offset: params.offset,
					limit: params.limit,
					level: level,
					parentCategoryId: $('#tb_category'+level).data('pcateid')
				}
			},                                  //传递参数（*）
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
			height: 450,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId: 'categoryId',             //每一行的唯一标识，一般为主键列
			showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
			cardView: false,                    //是否显示详细视图
	        detailView: false,                  //是否显示父子表
	        onLoadSuccess: function() {
	        	/*显示操作*/
	        	$('[data-toggle="tooltip"]').tooltip();
	        },
	        columns: [ {
	            field: 'categoryName',
	            title: '分类名称',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true
	        }, {
	        	field: 'action',
	        	title: '操作',
	        	align: 'center',
	        	valign: 'middle',
	        	formatter: function(value, row, index) {
	        		var btn_group = '<div class="btn-group btn-group-xs">';
	        			/*+ '<button class="btn btn-transparent"><i class="fa fa-eye"></i></button>'*/
	        		if(level != 3) {
		        		btn_group = btn_group
		        			+ '<button class="btn btn-transparent" onclick="toLowerLevel(' + (level+1) + ', ' + row.categoryId + ');" '
		        			+ 	'data-toggle="tooltip" data-placement="right" title="下级分类">'
		        			+ 	'<i class="fa fa-level-down"></i></button>';
	        		}
	        		if(parent.hasAuthority('CATE_EDIT')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="editModel(' + row.categoryId + ', ' + level + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="编辑">'
	        			+ 	'<i class="fa fa-pencil"></i></button>';
	        		}
	        		if(parent.hasAuthority('CATE_DEL'))	{
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="confirmDeleteModel(' + row.categoryId + ', ' + level + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="删除">'
	        			+ 	'<i class="fa fa-times"></i></button>';
	        		}
	        		btn_group = btn_group + '</div>';
	        		return btn_group;
	        	}
	    	} ]
		});
	};
	oTable.addEventListener = function() {
		$('.panel-title[data-level="'+level+'"]').click(function() {
			parentCategoryId = $('#tb_category'+level).data('pcateid');
			$('#tb_category'+level).bootstrapTable('refresh',{query: {parentCategoryId: parentCategoryId}});
		});
		$('#btn_add'+level).click(function() {
			parentCategoryId = $('#tb_category'+level).data('pcateid');
			$('#create_form')[0].reset();
			$('#create_form_level').val(level);
			$('#create_form_pcateid').val(parentCategoryId);
			$('#create_modal').modal('show');
		});
		$('#create_form_deploy').click(function() {
			if($('#create_form_level').val() == level){
				var param = $('#create_form').serialize();
				$.ajax({
					type: 'post',
					url: '/category/categoryCreate',
					dataType: 'json',
					data: param,
					success: function(data) {
						$('#create_modal').modal('hide');
						$('#create_form')[0].reset();
						parentCategoryId = $('#tb_category'+level).data('pcateid');
						$('#tb_category'+level).bootstrapTable('refresh',{query: {parentCategoryId: parentCategoryId}});
					}
				});				
			}
		});
		$('#edit_form_deploy').click(function() {
			if($('#edit_form_level').val() == level){
				var param = $('#edit_form').serialize();
				$.ajax({
					type: 'post',
					url: '/category/categoryEdit',
					dataType: 'json',
					data: param,
					success: function(data) {
						$('#edit_modal').modal('hide');
						$('#edit_form')[0].reset();
						parentCategoryId = $('#tb_category'+level).data('pcateid');
						$('#tb_category'+level).bootstrapTable('refresh',{query: {parentCategoryId: parentCategoryId}});
					}
				});
			}
		});
		$('#delete_deploy').click(function() {
			if($('#delete_deploy').data('level') == level) {
				var categoryId = $('#delete_deploy').data('cateid');
				$.ajax({
					type: 'post',
					url: '/category/categoryDelete',
					dataType: 'json',
					data: {
						'categoryId': categoryId
					},
					success: function(data) {
						$('#confirm_delete_model').modal('hide');
						parentCategoryId = $('#tb_category'+level).data('pcateid');
						$('#tb_category'+level).bootstrapTable('refresh',{query: {parentCategoryId: parentCategoryId}});
					}
				});
			}

		});
	};
	return oTable;
}

function confirmDeleteModel(categoryId, level) {
	$('#delete_deploy').data('cateid', categoryId);
	$('#delete_deploy').data('level', level);
	$('#confirm_delete_model').modal('show');
}

function editModel(categoryId, level) {
	$('#edit_form')[0].reset();
	$('#edit_form_level').val(level);
	$.ajax({
		type: 'get',
		url: '/category/categoryEditForm/' + categoryId,
		dataType: 'json',
		success: function(data) {
			$('#edit_form_categoryId').val(data.categoryId);
			$('#edit_form_categoryName').val(data.categoryName);
		}
	});
	$('#edit_modal').modal('show');
}

function toggleAccordion(level) {
	if(level == 1){
		$('#accordion1').collapse('toggle');
		$('#accordion2').collapse('hide');
		$('#accordion3').collapse('hide');
	}
	if(level == 2){
		$('#accordion1').collapse('hide');
		$('#accordion2').collapse('toggle');
		$('#accordion3').collapse('hide');
	}
	if(level == 3){
		$('#accordion1').collapse('hide');
		$('#accordion2').collapse('hide');
		$('#accordion3').collapse('toggle');
	}	
}

function toLowerLevel(level, parentCategoryId) {
	toggleAccordion(level);
	var params = {
		query: {
			parentCategoryId: parentCategoryId
		}
	}
	$('#tb_category'+level).data('pcateid', parentCategoryId);
	$('#tb_category'+level).bootstrapTable('refresh', params);
}
