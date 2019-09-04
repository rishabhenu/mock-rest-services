package com.mockservices.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "mockServicesMainController")
public class MockServicesMainController {
	
	@RequestMapping("/")
	public String getHome(HttpServletRequest request, HttpServletResponse response) {
		String sb = (request.getRequestURL()).substring(0, (request.getRequestURL().length()-request.getRequestURI().length()));
		String context = request.getContextPath();
		request.setAttribute("baseUrl", sb.toString()+context);
		request.setAttribute("context", context);
		return "index";
	}
}
