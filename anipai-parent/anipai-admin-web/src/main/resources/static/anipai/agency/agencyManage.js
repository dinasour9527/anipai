$(function(){
	var tb_agency = new Table();
	tb_agency.addEventListener();
	tb_agency.init();
	$('.modal').on('hide.bs.modal', function() {
		$('#animated-row').attr('class', 'row');
	});
});

var Table = function() {
	var oTable = new Object();
	oTable.init = function() {
	$('#tb_agency').bootstrapTable({
			url: parent.baseurl + '/agency/agencyTable',      
			method: 'get',                  
			toolbar: '#toolbar',              
			striped: true,                    
			cache: false,                     
			pagination: true,               
			sortable: true,                  
			sortOrder: 'asc',                   
			sortName: 'agencyId',
			sidePagination: 'server',       
			pageNumber:1,                   
			pageSize: 10,                   
			pageList: [10, 25, 50, 100],        
			showColumns: true,          
			showRefresh: true,          
			clickToSelect: true,        
			height: 480,                
			uniqueId: 'agencyId',       
			showToggle:true,            
			cardView: false,            
	        detailView: false,          
	        onLoadSuccess: function() {
	        	$("[data-toggle='tooltip']").tooltip();
	        },
	        columns: [ {
	            field: 'agencyName',
	            title: '机构名称',
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
	        		if(parent.hasAuthority('AGENCY_EDIT')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="editModel(' + row.agencyId + ');" '
	        			+ 	'data-toggle="tooltip" data-placement="right" title="编辑">'
	        			+ 	'<i class="fa fa-pencil"></i></button>';
	        		}
	        		if(parent.hasAuthority('AGENCY_DEL')) {
	        			btn_group = btn_group
	        			+ '<button class="btn btn-transparent" onclick="confirmDeleteModel(' + row.agencyId + ');" '
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
				url: parent.baseurl + '/agency/agencyCreate',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#create_modal').modal("hide");
					$('#create_form')[0].reset();
					$('#tb_agency').bootstrapTable('refresh');
				}
			});
		});
		$('#edit_form_deploy').click(function() {
			var param = $('#edit_form').serialize();
			$.ajax({
				type: 'post',
				url: parent.baseurl + '/agency/agencyEdit',
				dataType: 'json',
				data: param,
				success: function(data) {
					$('#edit_modal').modal("hide");
					$('#edit_form')[0].reset();
					$('#tb_agency').bootstrapTable('refresh');
				}
			});
		});
		$('#delete_deploy').click(function() {
			var agencyId = $('#delete_deploy').data('agencyid');
			$.ajax({
				type: 'post',
				url: parent.baseurl + '/agency/agencyDelete',
				dataType: 'json',
				data: {
					'agencyId': agencyId
				},
				success: function(data) {
					$('#confirm_delete_model').modal("hide");
	$('#tb_agency').bootstrapTable('refresh');
				}
			});
		});
	};
	return oTable;
}

function confirmDeleteModel(agencyId) {
	$('#delete_deploy').data('agencyid', agencyId);
	$('#confirm_delete_model').modal('show');
}

function editModel(agencyId) {
	$('#edit_form')[0].reset();
	$.ajax({
		type: 'get',
		url: parent.baseurl + '/agency/agencyEditForm/' + agencyId,
		dataType: 'json',
		success: function(data) {
			$('#edit_form #agencyId').val(data.agencyId);
			$('#edit_form #agencyName').val(data.agencyName);
		}
	});
	$('#edit_modal').modal('show');
}

