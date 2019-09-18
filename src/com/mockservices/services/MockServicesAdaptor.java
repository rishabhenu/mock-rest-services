package com.mockservices.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mockservices.req_resp.MockServiceRequest;
import com.mockservices.req_resp.MockServiceResponse;

@Component("mockServicesAdaptor")
public class MockServicesAdaptor extends AbstractServiceAdaptor {
	
	public static Map<String, MockServiceRequest> requestsResponsesPOST = new TreeMap<>();
	public static Map<String, MockServiceRequest> requestsResponsesGET = new TreeMap<>();
	
	private static Map<String, Object> allServices = new TreeMap<String, Object>();
	public ResponseEntity<MockServiceResponse> addService(String servicePath, MockServiceRequest request) {
		MockServiceResponse response = null;
		HttpStatus status = null;
		try {
			Map<String, String> service = new HashMap<String, String>();
			service.put("", "");
			allServices.put(servicePath, request);
			response = MockServiceResponse.getSuccessfulInstance("Service Created Successfully", service);
		}catch(Exception e) {
		}
		return new ResponseEntity<MockServiceResponse>(response, status);
	}
	
	public boolean setResponse(String path, MockServiceRequest request) {
		try {
			String method = request.getHeaders().getMethod();
			switch(method) {
			case "GET":
				requestsResponsesGET.put(path, request);
				break;
			case "POST":
				requestsResponsesPOST.put(path, request);
				break;
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public MockServiceRequest getResponse(String request, String method) {
		switch(method) {
		case "POST":
			return requestsResponsesPOST.get(request);
		case "GET":
			return requestsResponsesGET.get(request);
		}
		return null;
	}
	
	public List<MockServiceRequest> getAllServices() {
		List<MockServiceRequest> requestsList = new LinkedList<>();
		requestsList.addAll(requestsResponsesPOST.values());
		requestsList.addAll(requestsResponsesGET.values());
		return requestsList;
	}
	
	public void deleteService(String path, String method) throws Exception {
		switch(method) {
		case "POST":
			requestsResponsesPOST.remove(path);
			break;
		case "GET":
			requestsResponsesGET.remove(path);
			break;
		}
	}
}
