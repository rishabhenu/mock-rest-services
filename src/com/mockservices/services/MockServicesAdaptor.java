package com.mockservices.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MockServicesAdaptor {
	
	private static Map<String,String> requestsResponses = new HashMap<>();
	
	public boolean setResponse(String request, String response) {
		if(response == null)
			return false;
		try{
			requestsResponses.put(request, response);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Object getResponse(String request) {
		return requestsResponses.get(request);
	}
	
	public Map<String, String> getAllServices() {
		return requestsResponses;
	}
}
