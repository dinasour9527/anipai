$(function(){
	var tb_user = new Table();
	tb_user.addEventListener();
	tb_user.init();
	initRole();
	$('.modal').on('hide.bs.modal', function() {
		$('#animated-row').attr('class', 'row');
	});
});

var Table = function() {
	var oTable = new Object();
	oTable.init = function() {
		$('#tb_user').bootstrapTable({
			url: '/user/userTable',             //请求后台的URL（*）
			method: 'get',                      //请求方式（*）
			toolbar: '#toolbar',                //工具按钮用哪个容器
			striped: true,                      //是否显示行间隔色
			cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination: true,                   //是否显示分页（*）
			sortable: true,                     //是否启用排序
			sortOrder: 'asc',                   //排序方式
			sortName: 'userId',
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
			uniqueId: 'userId',                     //每一行的唯一标识，一般为主键列
			showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
			cardView: false,                    //是否显示详细视图
	        detailView: false,                  //是否显示父子表
	        onLoadSuccess: function() {
	        	$("[data-toggle='tooltip']").tooltip();
	        },
	        columns: [ /*{
	        	field: 'userId',
	        	title: '序号',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true
	        }, */{
	            field: 'userName',
	            title: '系统用户名',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true
	        }, {
	            field: 'roleName',
	            title: '角色',
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
	        		if(parent.hasAuthority('SYSUSER_ADD')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="editModel(' + row.userId + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="编辑">'
	        			+ 	'<i class="fa fa-pencil"></i></button>';
	        		}
	        		if(parent.hasAuthority('SYSUSER_DEL')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="confirmDeleteModel(' + row.userId + ');" '
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
		$('#btn_add').click(function() {
			$('#create_form')[0].reset();
			$('#create_form .role_group').val('').trigger('change');
			$('#create_modal').modal('show');
		});
		$('#create_form_deploy').click(function() {
			var param = $('#create_form').serialize();
			$.ajax({
				type: 'post',
				url: '/user/userCreate',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#create_modal').modal("hide");
					$('#create_form')[0].reset();
					$('#tb_user').bootstrapTable('refresh');
				}
			});
		});
		$('#edit_form_deploy').click(function() {
			var param = $('#edit_form').serialize();
			$.ajax({
				type: 'post',
				url: '/user/userEdit',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#edit_modal').modal("hide");
					$('#edit_form')[0].reset();
					$('#tb_user').bootstrapTable('refresh');
				}
			});
		});
		$('#delete_deploy').click(function() {
			var userId = $('#delete_deploy').data('userid');
			$.ajax({
				type: 'post',
				url: '/user/userDelete',
				dataType: 'json',
				data: {
					'userId': userId
				},
				success: function(data) {
					$('#confirm_delete_model').modal("hide");
					$('#tb_user').bootstrapTable('refresh');
				}
			});
		});
	};
	return oTable;
}

function initRole() {
	$.ajax({
		type: 'get',
		url: '/user/role',
		dataType: 'json',
		success: function(data) {
			$.each(data, function(index, obj) {
				var option = '<option value="' + obj.roleId + '">'
							 + obj.roleName
							 + '</option>'
				$('.role_group').append(option);
			});
			$('.role_group').select2({
				theme: 'bootstrap',
				placeholder: '请选择一个角色',
				allowClear: true
			});
		}
	});
}

function confirmDeleteModel(roleId) {
	$('#delete_deploy').data('userid', roleId);
	$('#confirm_delete_model').modal('show');
}

function editModel(userId) {
	$('#edit_form')[0].reset();
	$.ajax({
		type: 'get',
		url: '/user/userEditForm/' + userId,
		dataType: 'json',
		success: function(data) {
			$('#edit_form #userId').val(data.userId);
			$('#edit_form #userName').val(data.userName);
			$('#edit_form #role').val(data.roleId).trigger('change');
		}
	});
	$('#edit_modal').modal('show');
}

