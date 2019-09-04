package com.mockservices.resources;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.services.MockServicesAdaptor;

@RestController
@RequestMapping(path = "/rest/create")
public class MockServicesCreate {

	private static final Logger logger = LoggerFactory.getLogger(MockServicesCreate.class.getName());
	
	@Autowired
	private MockServicesAdaptor mockServicesAdaptor;
	
	@RequestMapping(consumes = { "application/json", "application/xml", "text/plain" }, produces = { "application/json",
			"application/xml", "text/plain" }, method = RequestMethod.POST, path = "/{url}")
	public @ResponseBody String createMockService(@RequestBody String req, @PathVariable(value = "url") String url,
						HttpServletRequest request, HttpServletResponse response) {
		String method = request.getHeader("Method-Name");
		if(method == null || method.isEmpty()) {
			return "{\"status\" : \"failure\", \"message\" : \"Method-Name header is not found\"}";
		}
		method = method.toUpperCase();
		logger.info("-----------------------Received request to create /" + url
				+ " service. Method : "+method+" ---------------------------------");
		logger.info("Request : " + req);
		String resp;
		if (mockServicesAdaptor.setResponse(url, req, method))
			resp = "{\"status\" : \"success\"}";
		else
			resp = "{\"status\" : \"failure\"}";
		logger.info("-------------------------------------------------------------------------------------");
		return resp;
	}
	
	@RequestMapping(consumes = { "application/json", "application/xml", "text/plain" }, produces = { "application/json",
			"application/xml", "text/plain" }, method = RequestMethod.POST, path = "/{url}/{url1}")
	public @ResponseBody String createMockServiceExtraParam(@RequestBody String req, @PathVariable(value = "url") String url,
									HttpServletRequest request, HttpServletResponse response) {
		return createMockService(req, url, request, response);
	}
	
}
