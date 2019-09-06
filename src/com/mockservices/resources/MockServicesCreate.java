package com.mockservices.resources;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.services.MockServicesAdaptor;

@RestController
@RequestMapping(path = "/rest/create")
public class MockServicesCreate extends AbstractMockServices {

	private static final Logger logger = LoggerFactory.getLogger(MockServicesCreate.class.getName());
	
	@Autowired
	private MockServicesAdaptor mockServicesAdaptor;
	
	@RequestMapping(
			consumes = { "application/json", "application/xml", "text/plain" }, 
			produces = { "application/json" }, 
			method = RequestMethod.POST,
			path = {"/{url}", "/{url}/{url1}"}
		)
	public @ResponseBody ResponseEntity<Object> createMockService(RequestEntity<String> request, 
																	@PathVariable(value = "url") String url) {
		logger.info("----------------------- Received request to create /" + url
				+ " service. ---------------------------------");
		System.out.println(request.getUrl());
		System.out.println("Host : "+request.getUrl().getHost());
		Map<String, Object> response = new HashMap<>();
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("content-type", Constants.CONTENT_TYPE.JSON.stringValue());
		
		List<String> headerList = request.getHeaders().get("Method-Name");
		if(headerList == null || headerList.isEmpty()) {
			response.put("status", "failure");
			response.put("message", "Header 'Method-Name' not found");
			return new ResponseEntity<Object>(response, headers, HttpStatus.OK);
		}
		String method = headerList.get(0).toUpperCase();
		String req = request.getBody();
		logger.info("Method : " + method +", Request : " + req);
		if (mockServicesAdaptor.setResponse(url, req, method)) {
			response.put("status", "success");
			
			Map<String, String> service = new HashMap<String, String>();
			service.put("method", method);
			service.put("contentType", getContentType(req));
			service.put("url", request.getUrl().toString().replace("/rest/create/", "/rest/generate/"));
			
			response.put("service", service);
		}
		else
			response.put("status", "failure");
		logger.info("-------------------------------------------------------------------------------------");
		
		return new ResponseEntity<Object>(response, headers, HttpStatus.OK);
	}
	
}
