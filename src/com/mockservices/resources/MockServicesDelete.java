package com.mockservices.resources;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.services.MockServicesAdaptor;

@RestController
@RequestMapping("/rest/delete")
public class MockServicesDelete {

	private static final Logger logger = LoggerFactory.getLogger(MockServicesDelete.class.getName());
	
	@Autowired
	private MockServicesAdaptor mockServicesAdaptor;
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{url}", produces = "application/json")
	public String deleteMockService(@PathVariable("url") String url, HttpServletRequest request) {
		logger.info("---------------------- Got request to delete /" + url
				+ " service. --------------------------------------");
		String response = "";
		try {
			String method = request.getHeader("Method-Name");
			if(method == null || method.isEmpty()) {
				return "{\"status\" : \"failure\", \"message\" : \"Header 'Method-Name' not found\"}";
			}
			method = method.toUpperCase();
			mockServicesAdaptor.deleteService(url, method);
			response = "{\"status\":\"success\",\"msg\":\"deleted successfully\"}";
			logger.info("------------------------ Deleted successfully ------------------------------");
		} catch (Exception e) {
			response = "{\"status\":\"failure\",\"msg\":\"" + e.getMessage() + "\"}";
			logger.info("------------------ Couldn't delete. Reason : "+ e.getMessage()+ "------------------- ");
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/get-all-services")
	public @ResponseBody String getAllServices() {
		if (mockServicesAdaptor.getAllServices() == null || mockServicesAdaptor.getAllServices().size() == 0) {
			return "{\"status\":\"success\",\"message\":\"No any service created as of now\"}";
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

}
