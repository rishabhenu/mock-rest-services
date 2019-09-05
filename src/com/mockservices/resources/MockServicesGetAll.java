package com.mockservices.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.resources.AbstractMockServices.Constants.HTTP_METHOD_TYPE_STRING;
import com.mockservices.services.MockServicesAdaptor;

@RestController
@RequestMapping("/getallservices")
public class MockServicesGetAll extends AbstractMockServices {

	@Autowired
	private MockServicesAdaptor mockServicesAdaptor;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<Object>> getAllServices() {
		MultiValueMap<String, String> headers = new HttpHeaders();
		List<Object> response = new ArrayList<Object>();

		Map<String, Map<String, String>> allServices = mockServicesAdaptor.getAllServices();
		for (HTTP_METHOD_TYPE_STRING method : HTTP_METHOD_TYPE_STRING.values()) {
			Map<String, String> methodNameAndServiceNameResponseMap = allServices.get(method.stringValue());
			
			// Adding all the services, with their content-types in one single map //
			List<Map<String, String>> methodWiseAllServicesList = new ArrayList<Map<String,String>>();
			for (String serviceName : methodNameAndServiceNameResponseMap.keySet()) {
				Map<String, String> serviceNameContentTypeMap = new HashMap<String, String>();
				serviceNameContentTypeMap.put("path", "/"+serviceName.replaceAll("#", "/"));
				serviceNameContentTypeMap.put("responseType", getContentType(methodNameAndServiceNameResponseMap.get(serviceName)));
				
				methodWiseAllServicesList.add(serviceNameContentTypeMap);
			}
			Map<String, Object> methodNameAndServiceMap = new HashMap<String, Object>();
			methodNameAndServiceMap.put(method.stringValue(), methodWiseAllServicesList);
			response.add(methodNameAndServiceMap);
		}

		return new ResponseEntity<List<Object>>(response, headers, HttpStatus.OK);
	}
}
