package com.anipai.passport.service;

import java.util.Map;

public interface CustomerService {

	Long createCustomer(String username, String email, String password);

	Map<String, Object> getCustomerListPage(String sort, String order, int offset, int limit, Long agencyId);

}
