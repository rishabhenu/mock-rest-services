package com.mockservices.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.services.MockServicesAdaptor;

@RestController
@RequestMapping(path = "/rest/generate")
public class MockServicesGenerate {

	private static final Logger logger = LoggerFactory.getLogger(MockServicesGenerate.class.getName());

	@Autowired
	private MockServicesAdaptor mockServicesAdaptor;

	@RequestMapping(consumes = { "application/json", "application/xml",
			"text/plain" }, method = RequestMethod.POST, path = "/{url}")
	public @ResponseBody String getMockResponsePOST(@RequestBody String req, @PathVariable(value = "url") String url,
			HttpServletRequest request, HttpServletResponse response) {

		String method = request.getMethod().toUpperCase();
		logger.info("-----------------------Received request to generate /" + url + " service. Method : " + method
				+ " ---------------------------------");
		String resp = null;
		if (mockServicesAdaptor.getResponse(url, request.getMethod().toUpperCase()) != null) {
			resp = (String) mockServicesAdaptor.getResponse(url, method);
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
		} else {
			resp = "{\"status\":\"failure\",\"status-message\":\"This service is not created yet please create first\"}";
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
		}
		setContentType(response, resp);
		return resp;
	}

	@RequestMapping(consumes = { "application/json", "application/xml",
			"text/plain" }, method = RequestMethod.POST, path = "/{url}/{vin}")
	public @ResponseBody String getResponseWithTwoParamsPOST(String req, @PathVariable(value = "url") String url,
			HttpServletRequest request, HttpServletResponse response) {
		return getMockResponsePOST(req, url, request, response);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{url}")
	public @ResponseBody String getMockResponseGET( @PathVariable(value = "url") String url,
			HttpServletRequest request, HttpServletResponse response) {

		String method = request.getMethod().toUpperCase();
		logger.info("-----------------------Received request to generate /" + url + " service. Method : " + method
				+ " ---------------------------------");
		String resp = null;
		if (mockServicesAdaptor.getResponse(url, request.getMethod().toUpperCase()) != null) {
			resp = (String) mockServicesAdaptor.getResponse(url, method);
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
		} else {
			resp = "{\"status\":\"failure\",\"status-message\":\"This service is not created yet please create first\"}";
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
		}
		setContentType(response, resp);
		return resp;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{url}/{vin}")
	public @ResponseBody String getResponseWithTwoParamsGET(@PathVariable(value = "url") String url,
			HttpServletRequest request, HttpServletResponse response) {
		return getMockResponseGET(url, request, response);
	}
	
	private void setContentType(HttpServletResponse response, String resp) {
		if(resp != null && !resp.isEmpty()) {
			if(resp.trim().charAt(0) == '{' && resp.trim().charAt(resp.trim().length()-1) == '}') {
				response.setContentType("application/json");
			}else if(resp.trim().charAt(0) == '<' && resp.trim().charAt(resp.trim().length()-1) == '>') {
				response.setContentType("application/xml");
			}
		}
	}
}
