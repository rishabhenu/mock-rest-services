package com.mockservices.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockservices.services.MockServicesAdaptor;

@RestController(value = "mockServices")
public class MockServices {

	private static final Logger logger = Logger.getLogger(MockServices.class.getName());
	private MockServicesAdaptor mockServicesAdaptor;

	@RequestMapping(consumes = { "application/json", "application/xml", "text/plain" }, produces = { "application/json",
			"application/xml", "text/plain" }, method = RequestMethod.POST, path = "/rest/create/{url}")
	public @ResponseBody String createNewResponse(@RequestBody String req, @PathVariable(value = "url") String url) {
		logger.info("-----------------------Received request to create /" + url
				+ " service---------------------------------");
		logger.info("Request : " + req);
		String resp;
		if (mockServicesAdaptor.setResponse(url, req))
			resp = "{\"status\" : \"success\"}";
		else
			resp = "{\"status\" : \"failure\"}";
		logger.info("-------------------------------------------------------------------------------------");
		return resp;
	}

	@RequestMapping(consumes = { "application/json", "application/xml", "text/plain" }, produces = { "application/json",
			"application/xml", "text/plain" }, method = RequestMethod.POST, path = "/rest/generate/{url}")
	public @ResponseBody String getResponse(@RequestBody String req, @PathVariable(value = "url") String url) {

		logger.info("-----------------------Received request to generate /" + url
				+ " service---------------------------------");
		String resp = null;
		if (mockServicesAdaptor.getResponse(url) != null) {
			resp = (String) mockServicesAdaptor.getResponse(url);
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
			return resp;
		} else {
			resp = "{\"status\":\"failure\",\"status-message\":\"This service is not created yet please create first\"}";
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
			return resp;
		}
	}

	@RequestMapping(consumes = { "application/json", "application/xml", "text/plain" }, produces = { "application/json",
			"application/xml", "text/plain" }, method = RequestMethod.POST, path = "/rest/generate/{url}/{vin}")
	public String getRmsPricingResponse(String req, @PathVariable(value = "url") String url) {
		return getResponse(req, url);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/get-all-services", produces = { "application/json" })
	public @ResponseBody String getAllServices() throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		if (mockServicesAdaptor.getAllServices() == null || mockServicesAdaptor.getAllServices().size() == 0) {
			ObjectMapper mapper = new ObjectMapper();
			map.put("status", "success");
			map.put("message", "No any service created as of now");
			return mapper.writeValueAsString(map);
		} else {
			String response = "{\"status\" : \"success\"," + "\"services\":[";
			Map<String, String> allServices = mockServicesAdaptor.getAllServices();
			int index = 0;
			for (String service : allServices.keySet()) {
				if (index != 0)
					response += ",";
				response += "{\"" + service + "\":" + allServices.get(service) + "}";
				index++;
			}
			response += "]}";
			return response;
		}
	}

	public void setMockServicesAdaptor(MockServicesAdaptor mockServicesAdaptor) {
		this.mockServicesAdaptor = mockServicesAdaptor;
	}

}
