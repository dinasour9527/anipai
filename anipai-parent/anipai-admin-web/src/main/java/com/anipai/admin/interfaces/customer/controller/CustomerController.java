package com.anipai.admin.interfaces.customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anipai.admin.interfaces.customer.dto.CustomerDTO;
import com.anipai.admin.interfaces.customer.dto.CustomerDTOConverter;
import com.anipai.admin.interfaces.system.dto.RoleDTO;
import com.anipai.admin.interfaces.system.dto.RoleDTOConverter;
import com.anipai.admin.utils.PageModel;
import com.anipai.passport.domain.Customer;
import com.anipai.passport.service.CustomerService;
import com.anipai.system.domain.SysRole;
import com.anipai.system.security.CurrentUser;
import com.anipai.system.security.SysUserDetails;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/customerManage", method = RequestMethod.GET)
	public String customerManage() {
		return "customer/customerManage";
	}
	
	@RequestMapping(value = "/customerTable", method = RequestMethod.GET)
	@ResponseBody
	public PageModel<CustomerDTO> customerTable(@CurrentUser SysUserDetails currentUser,
			String sort, String order, int offset, int limit) {
		Map<String, Object> customerListPage = customerService.getCustomerListPage(sort, order, offset, limit,
				currentUser.getAgencyId());
		@SuppressWarnings("unchecked")
		List<Customer> customerList = (List<Customer>) customerListPage.get("list");
		PageModel<CustomerDTO> pageModel = new PageModel<CustomerDTO>();
		List<CustomerDTO> customerDTOList = CustomerDTOConverter.toDTOList(customerList);
		pageModel
			.setRows(customerDTOList)
			.setTotal((int) customerListPage.get("total"));
		return pageModel;
	}
}
