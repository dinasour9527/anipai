package com.anipai.passport.domain;

import com.anipai.agency.domain.Agency;

public class Customer {
	private Long customerId;
	private String customerName;
	private String password;;
	private String email;
	private Agency agency;
	
	public Customer() {
	}
	public Customer(String customerName, String password, String email, Agency agency) {
		this.customerName = customerName;
		this.password = password;
		this.email = email;
		this.agency = agency;
	}
	public Customer(Long customerId, String customerName, String password, String email, Agency agency) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.password = password;
		this.email = email;
		this.agency = agency;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
}
