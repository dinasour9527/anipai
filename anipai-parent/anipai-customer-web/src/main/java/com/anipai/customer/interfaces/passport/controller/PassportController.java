package com.anipai.customer.interfaces.passport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anipai.passport.service.CustomerService;

@Controller
@RequestMapping("/passport")
public class PassportController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "passport/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(String username, String email, String password) {
		customerService.createCustomer(username, email, password);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "passport/login";
	}
}
