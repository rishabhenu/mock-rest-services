package com.mockservices.resources;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(
			consumes = { "application/json", "application/xml", "text/plain" }, 
			produces = { "application/json" }, 
			method = RequestMethod.POST, 
			path = {"/{url}", "/{url}/{url1}"}
		)
	public @ResponseBody String createMockService(RequestEntity<String> request, @PathVariable(value = "url") String url) {
		List<String> headerList = request.getHeaders().get("Method-Name");
		if(headerList == null || headerList.isEmpty()) {
			return "{\"status\" : \"failure\", \"message\" : \"Header 'Method-Name' not found\"}";
		}
		String method = headerList.get(0).toUpperCase();
		String req = request.getBody();
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
	
}
