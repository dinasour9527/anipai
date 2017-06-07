package com.anipai.admin.interfaces.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anipai.system.security.CurrentUser;
import com.anipai.system.security.SysUserDetails;

@Controller
@RequestMapping("/")
public class PortalController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model, @CurrentUser SysUserDetails currentUser) {
		model.addAttribute("currentUser", currentUser);
		return "portal/index";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
}
