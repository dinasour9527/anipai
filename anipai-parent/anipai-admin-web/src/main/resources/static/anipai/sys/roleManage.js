$(function(){
	var tb_role = new Table();
	tb_role.addEventListener();
	tb_role.init();
	initAuthority();
	$('.modal').on('hide.bs.modal', function() {
		$('#animated-row').attr('class', 'row');
	});
});

var Table = function() {
	var oTable = new Object();
	oTable.init = function() {
		$('#tb_role').bootstrapTable({
			url: parent.baseurl + '/user/roleTable',             //请求后台的URL（*）
			method: 'get',                      //请求方式（*）
			toolbar: '#toolbar',                //工具按钮用哪个容器
			striped: true,                      //是否显示行间隔色
			cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination: true,                   //是否显示分页（*）
			sortable: true,                     //是否启用排序
			sortOrder: 'asc',                   //排序方式
			sortName: 'roleId',
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
			uniqueId: 'roleId',                     //每一行的唯一标识，一般为主键列
			showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
			cardView: false,                    //是否显示详细视图
	        detailView: false,                  //是否显示父子表
	        onLoadSuccess: function() {
	        	$("[data-toggle='tooltip']").tooltip();
	        },
	        columns: [ /*{
	        	field: 'roleId',
	        	title: '序号',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true
	        },*/ {
	            field: 'roleCode',
	            title: '角色编码',
	        	align: 'center',
	        	valign: 'middle',
	        	sortable: true
	        }, {
	            field: 'roleName',
	            title: '角色名称',
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
	        		if(parent.hasAuthority('ROLE_EDIT')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="editModel(' + row.roleId + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="编辑">'
	        			+ 	'<i class="fa fa-pencil"></i></button>';
	        		}
	        		if(parent.hasAuthority('ROLE_DEL'))	{
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="confirmDeleteModel(' + row.roleId + ');" '
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
			$('#create_modal').modal('show');
		});
		$('#create_form_deploy').click(function() {
			var param = $('#create_form').serialize();
			$.ajax({
				type: 'post',
				url: parent.baseurl + '/user/roleCreate',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#create_modal').modal("hide");
					$('#create_form')[0].reset();
					$('#tb_role').bootstrapTable('refresh');
				}
			});
		});
		$('#edit_form_deploy').click(function() {
			var param = $('#edit_form').serialize();
			$.ajax({
				type: 'post',
				url: parent.baseurl + '/user/roleEdit',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#edit_modal').modal("hide");
					$('#edit_form')[0].reset();
					$('#tb_role').bootstrapTable('refresh');
				}
			});
		});
		$('#delete_deploy').click(function() {
			var roleId = $('#delete_deploy').data('roleid');
			$.ajax({
				type: 'post',
				url: parent.baseurl + '/user/roleDelete',
				dataType: 'json',
				data: {
					'roleId': roleId
				},
				success: function(data) {
					$('#confirm_delete_model').modal("hide");
					$('#tb_role').bootstrapTable('refresh');
				}
			});
		});
	};
	return oTable;
}

function initAuthority() {
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/user/authority',
		dataType: 'json',
		success: function(data) {
			$.each(data, function(index, obj) {
				var checkbox = 
				 	'<label class="checkbox-inline">'
				+		'<input class="edit_form_input" type="checkbox" '
				+		'name="authorityIds" value="' + obj.authorityId + '"/>'
				+ 		obj.authorityName
				+	'</label>'
				$('.auth_group').append(checkbox);
			});
		}
	});
}

function confirmDeleteModel(roleId) {
	$('#delete_deploy').data('roleid', roleId);
	$('#confirm_delete_model').modal('show');
}

function editModel(roleId) {
	$('#edit_form .auth_group input').removeAttr('checked');
	$('#edit_form')[0].reset();
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/user/roleEditForm/' + roleId,
		dataType: 'json',
		success: function(data) {
			console.log(data);
			$('#edit_form #roleId').val(data.role.roleId);
			$('#edit_form #roleCode').val(data.role.roleCode);
			$('#edit_form #roleName').val(data.role.roleName);
			$.each(data.authorities, function(index, obj) {
				$('#edit_form input[value=\'' + obj.authorityId + '\']')
					.attr('checked', '1');
			})
		}
	});
	$('#edit_modal').modal('show');
}

