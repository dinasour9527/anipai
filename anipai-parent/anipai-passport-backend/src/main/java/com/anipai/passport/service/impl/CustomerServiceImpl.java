package com.anipai.passport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.agency.domain.Agency;
import com.anipai.passport.domain.Customer;
import com.anipai.passport.mapper.CustomerMapper;
import com.anipai.passport.service.CustomerService;
import com.anipai.utils.Pagination;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public Long createCustomer(String username, String email, String password) {
		String encodePassword = new Md5PasswordEncoder().encodePassword(password, null);
		Agency agency = new Agency(new Long(1), null);
		Customer customer = new Customer(username, encodePassword, email, agency);
		customerMapper.insertCustomer(customer);
		return customer.getCustomerId();
	}

	@Override
	public Map<String, Object> getCustomerListPage(String sort, String order, int offset, int limit, Long agencyId) {
		Map<String, Object> customerListPage = new HashMap<String, Object>();
		Pagination pagination = new Pagination(sort, order, offset, limit);
		List<Customer> customerList = customerMapper.findCustomerPage(pagination, agencyId);
		int total = customerMapper.total(agencyId);
		customerListPage.put("list", customerList);
		customerListPage.put("total", total);
		return customerListPage;
	}

}
