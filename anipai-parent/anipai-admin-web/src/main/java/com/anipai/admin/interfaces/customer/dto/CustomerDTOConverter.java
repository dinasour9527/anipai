package com.anipai.admin.interfaces.customer.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.passport.domain.Customer;

public class CustomerDTOConverter {
	public static CustomerDTO toDTO(Customer customer) {
		return new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getEmail());
	}
	
	public static List<CustomerDTO> toDTOList(List<Customer> customerList) {
		List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
		for(Customer customer : customerList){
			customerDTOList.add(CustomerDTOConverter.toDTO(customer));
		}
		return customerDTOList;
	}
}
