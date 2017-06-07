$(function(){
	var tb_auction = new Table();
	tb_auction.addEventListener();
	tb_auction.init();
	$('#create_form_beginTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii'
	});
	$('#create_form_endTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii'
	});
	$('#edit_form_beginTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii'
	});
	$('#edit_form_endTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii'
	});
	if(createFlag == 1) {
		$('#create_form')[0].reset();
		$('#create_form_goodsId').val(goods.goodsId);
		$('#create_form_auctionName').val(goods.goodsName);
		$('#create_form_qtyAuction').val(goods.qtyTotal-goods.qtyAuction);
		$('#create_form_qtyAuction').spinner({
			min: 1,
			max: goods.qtyTotal-goods.qtyAuction
		});
		$('#create_modal').modal('show');
	}
	$('.modal').on('hide.bs.modal', function() {
		$('#animated-row').attr('class', 'row');
	});
	/*var spinner = '<span class="input-group-btn">'
		+ '<button type="button" class="btn btn-default" data-value="-1" data-target="#create_form_qtyAuction2" data-toggle="spinner">'
		+ '<span class="fa fa-minus"></span>'
		+ '</button>'
		+ '</span>'
		+ '<input id="create_form_qtyAuction2" data-ride="spinner" name="qtyAuction" type="text" class="form-control input-number" value="1"/>'
		+ '<span class="input-group-btn">'
		+ '<button type="button" class="btn btn-default" data-value="1" data-target="#create_form_qtyAuction2" data-toggle="spinner">'
		+ '<span class="fa fa-plus"></span>'
		+ '</button>'
		+ '</span>';
	$('#spinner-input-group').html(spinner);*/
	/*$('#edit_form_qtyAuction').spinner({
		min: 1,
		max: 5
	});*/
});

var Table = function() {
	var oTable = new Object();
	oTable.init = function() {
		$('#tb_auction').bootstrapTable({
			url: '/auction/auctionTable',             //请求后台的URL（*）
			method: 'get',                      //请求方式（*）
			toolbar: '#toolbar',                //工具按钮用哪个容器
			striped: true,                      //是否显示行间隔色
			cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination: true,                   //是否显示分页（*）
			sortable: true,                     //是否启用排序
			sortOrder: 'asc',                   //排序方式
			sortName: 'auctionId',
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
			uniqueId: 'auctionId',                     //每一行的唯一标识，一般为主键列
			showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
			cardView: false,                    //是否显示详细视图
	        detailView: false,                  //是否显示父子表
	        onLoadSuccess: function() {
	        	$("[data-toggle='tooltip']").tooltip();
	        },
	        columns: [ {
	            field: 'auctionName',
	            title: '拍卖名称',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true
	        }, {
	            field: 'beginTime',
	            title: '开始时间',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true
	        }, {
	            field: 'endTime',
	            title: '结束时间',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true,
	        },{
	            field: 'state',
	            title: '拍卖状态',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true,
	        	formatter: function (value, row, index) {
	        		if(value == 0) {
	        			return "未开始";
	        		}
	        		if(value == 1) {
	        			return "进行中";
	        		}
	        		if(value == 2) {
	        			return "已结束";
	        		}
	        	}
	        },  {
	        	field: 'action',
	        	title: '操作',
	        	align: 'center',
	        	valign: 'middle',
	        	formatter: function(value, row, index) {
	        		var btn_group = '<div class="btn-group btn-group-xs">';
	        			/*+ '<button class="btn btn-transparent"><i class="fa fa-eye"></i></button>'*/
	        		if(parent.hasAuthority('AUCTION_ADD')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="editModel(' + row.auctionId + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="编辑">'
	        			+ 	'<i class="fa fa-pencil"></i></button>';
	        		}
	        		if(parent.hasAuthority('AUCTION_DEL')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="confirmDeleteModel(' + row.auctionId + ');" '
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
			$('#create_form .role_group').val('').trigger('change');
			$('#create_modal').modal('show');
		});
		$('#create_form_deploy').click(function() {
			var param = $('#create_form').serialize();
			$.ajax({
				type: 'post',
				url: '/auction/auctionCreate',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#create_modal').modal("hide");
					$('#create_form')[0].reset();
					$('#tb_auction').bootstrapTable('refresh');
				}
			});
		});
		$('#edit_form_deploy').click(function() {
			var param = $('#edit_form').serialize();
			$.ajax({
				type: 'post',
				url: '/auction/auctionEdit',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#edit_modal').modal("hide");
					$('#edit_form')[0].reset();
					$('#tb_auction').bootstrapTable('refresh');
				}
			});
		});
		$('#delete_deploy').click(function() {
			var auctionId = $('#delete_deploy').data('auctionid');
			$.ajax({
				type: 'post',
				url: '/auction/auctionDelete',
				dataType: 'json',
				data: {
					'auctionId': auctionId
				},
				success: function(data) {
					$('#confirm_delete_model').modal("hide");
					$('#tb_auction').bootstrapTable('refresh');
				}
			});
		});
	};
	return oTable;
}

function confirmDeleteModel(auctionId) {
	$('#delete_deploy').data('auctionid', auctionId);
	$('#confirm_delete_model').modal('show');
}

function editModel(auctionId) {
	$('#edit_form')[0].reset();
	$.ajax({
		type: 'get',
		url: '/auction/auctionEditForm/' + auctionId,
		dataType: 'json',
		success: function(data) {
			$('#edit_form_auctionId').val(data.auctionId);
			$('#edit_form_goodsId').val(data.goodsId);
			$('#edit_form_auctionName').val(data.auctionName);
			$('#edit_form_beginTime').val(data.beginTime);
			$('#edit_form_endTime').val(data.endTime);
			$('#edit_form_qtyAuction').val(data.qtyAuction);
			$('#edit_form_qtyAuction').spinner('setOptions', {
				min: 1,
				max: data.qtyGoodsResidue + data.qtyAuction
			});
			$('#edit_form_intro').val(data.intro);
		}
	});
	$('#edit_modal').modal('show');
}

