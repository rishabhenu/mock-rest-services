package com.mockservices.services;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.mockservices.resources.AbstractMockServices;

public class MockServicesAdaptor extends AbstractMockServices {
	
	private static Map<String,String> requestsResponsesGET = new TreeMap<>();
	private static Map<String,String> requestsResponsesPOST = new TreeMap<>();
	
	public boolean setResponse(String request, String response, String method) {
		if(response == null)
			return false;
		try{
			switch(method) {
			case "POST":
				requestsResponsesPOST.put(request, response);
				break;
			case "GET":
				requestsResponsesGET.put(request, response);
				break;
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Object getResponse(String request, String method) {
		switch(method) {
		case "POST":
			return requestsResponsesPOST.get(request);
		case "GET":
			return requestsResponsesGET.get(request);
		}
		return "\"status\":\"failure\",\"message\":\"No method found\"";
	}
	
	public Map<String, Map<String,String>> getAllServices() {
		Map<String, Map<String,String>> allServices = new HashMap<>();
		allServices.put(Constants.HTTP_METHOD_TYPE_STRING.GET.toString(), requestsResponsesGET);
		allServices.put(Constants.HTTP_METHOD_TYPE_STRING.POST.toString(), requestsResponsesPOST);
		return allServices;
	}
	
	public boolean deleteService(String url, String method) throws Exception {
		try{
			String value = null;
			switch(method) {
			case "POST":
				value = requestsResponsesPOST.remove(url);
				break;
			case "GET":
				value = requestsResponsesGET.remove(url);
				break;
				default:
					throw new Exception("Method not allowed");
			}
			if(value == null) {
				throw new Exception("No service exists");
			}
			return true;
		}catch(Exception e) {
			throw e;
		}
	}
}
