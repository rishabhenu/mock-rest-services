package com.mockservices.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MockServicesMainController {
	
	@RequestMapping("/")
	public String getHomePage() {
		return "index";
	}

}
