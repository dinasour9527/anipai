package com.anipai.admin.interfaces.customer.dto;

public class CustomerDTO {
	private Long customerId;
	private String customerName;
	private String email;
	public CustomerDTO(Long customerId, String customerName, String email) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
