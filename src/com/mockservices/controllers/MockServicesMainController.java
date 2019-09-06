package com.mockservices.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "mockServicesMainController")
public class MockServicesMainController {
	
	@RequestMapping("/")
	public String getHome(HttpServletRequest request, HttpServletResponse response) {
		String sb = (request.getRequestURL()).substring(0, (request.getRequestURL().length()-request.getRequestURI().length()));
		
		String context = request.getContextPath();
		if(!isLocalHost(sb) && sb.contains("http://")) {
			sb = sb.replace("http://", "https://");
		}
		request.setAttribute("baseUrl", sb.toString()+context);
		request.setAttribute("context", context);
		return "index";
	}
	
	private boolean isLocalHost(String url) {
		String regex = "(.*)([a-zA-Z])(:)([0-9]+)";
		boolean b = url.matches(regex);
		return b;
	}
	
	public static void main(String...strings) {
		String url = "http://localhost:8082";
		String regex = "(.*)([a-zA-Z])(:)([0-9]+)";
		boolean b = url.matches(regex);
		System.out.println(b);
	}
}
