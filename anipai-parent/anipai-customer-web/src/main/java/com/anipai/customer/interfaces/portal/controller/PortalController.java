package com.anipai.customer.interfaces.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anipai.passport.security.CurrentCustomer;
import com.anipai.passport.security.CustomerDetails;

@Controller
@RequestMapping("/")
public class PortalController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model, @CurrentCustomer CustomerDetails currentCustomer) {
		model.addAttribute("currentCustomer", currentCustomer);
		return "portal/index";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "portal/main";
	}
}
