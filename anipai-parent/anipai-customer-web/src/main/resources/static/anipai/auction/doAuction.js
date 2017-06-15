$(function () {
    initClock();
    var tb_bid = new Table();
    tb_bid.init();
});

var Table = function() {
	var oTable = new Object();
	oTable.init = function() {
		$('#tb_bid').bootstrapTable({
			url: parent.baseurl + '/bid/bidRecord',        //请求后台的URL（*）
			method: 'get',                      //请求方式（*）
			//toolbar: '#toolbar',                //工具按钮用哪个容器
			striped: true,                      //是否显示行间隔色
			cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination: false,                   //是否显示分页（*）
			sortable: true,                     //是否启用排序
			sortOrder: 'desc',                   //排序方式
			sortName: 'bidTime',
			//queryParams: oTableInit.queryParams,//传递参数（*）
			//sidePagination: 'server',           //分页方式：client客户端分页，server服务端分页（*）
			//pageNumber:1,                       //初始化加载第一页，默认第一页
			//pageSize: 10,                       //每页的记录行数（*）
			//pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
			//search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			//strictSearch: false,
			//showColumns: true,                  //是否显示所有的列
			//showRefresh: true,                  //是否显示刷新按钮
			//minimumCountColumns: 2,             //最少允许的列数
			clickToSelect: true,                //是否启用点击选中行
			height: 480,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId: 'bidId',                     //每一行的唯一标识，一般为主键列
			//showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
			//cardView: false,                    //是否显示详细视图
	        //detailView: false,                  //是否显示父子表
	        onLoadSuccess: function(data) {
	        	$('#highestbid').html('¥'+data[0].bidPrice)
	        	$('#bidPrice').val(data[0].bidPrice+1);
	        	$('#bidPrice').spinner({
	    			min: data[0].bidPrice+1
	    		});
	        },
	        columns: [ {
	            field: 'customerId',
	            title: '竞买人Id',
	        	align: 'center',
	        	valign: 'middle'
	        }, {
	            field: 'bidPrice',
	            title: '竞价',
	        	align: 'center',
	        	valign: 'middle',
	        	formatter: function(value, row, index) {
	        		return '¥'+value;
	        	}
	        }, {
	        	field: 'bidTime',
	        	title: '竞价时间',
	        	align: 'center',
	        	valign: 'middle'
	    	} ]
		});
	};
	return oTable;
}

function initClock() {
	$('.your-clock').FlipClock(auction.remainTime, {
		clockFace: 'DailyCounter',
		countdown: true,
	});
	$('.flip-clock-wrapper a').attr('href', 'javascript:;');
}

function doBid() {
	var param = $('#bid_form').serialize();
	$.ajax({
		type: 'post',
		url: parent.baseurl + '/bid',
		dataType: 'json',
		data: param,
		success: function(data) {
			$('#tb_bid').bootstrapTable('refresh');
		}
	});
}