package com.mockservices.services;

import java.util.HashMap;
import java.util.Map;

public class MockServicesAdaptor {
	
	private static Map<String,String> requestsResponsesGET = new HashMap<>();
	private static Map<String,String> requestsResponsesPOST = new HashMap<>();
	
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
	
	public Map<String, String> getAllServices() {
		return requestsResponsesPOST;
	}
	
	public boolean deleteService(String url, String method) {
		try{
			switch(method) {
			case "POST":
				requestsResponsesPOST.remove(url);
				break;
			case "GET":
				requestsResponsesGET.remove(url);
				break;
			}
			return true;
		}catch(Exception e) {
			throw e;
		}
	}
}
