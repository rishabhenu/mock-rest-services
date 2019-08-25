package com.mockservices.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "mockServicesMainController")
public class MockServicesMainController {
	
	@RequestMapping("/")
	public String getHome(Model model) {
		model.addAttribute("name", "Rishabh Sharma");
		return "index";
	}
	
}
