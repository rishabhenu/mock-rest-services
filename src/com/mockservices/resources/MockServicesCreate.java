package com.mockservices.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.req_resp.MockServiceRequest;

@RestController
@RequestMapping(path = "/rest/create")
public class MockServicesCreate extends MockServicesCommon {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@CrossOrigin(value = { "*" })
	@RequestMapping(method = { RequestMethod.POST }, produces = "application/json" )
	public ResponseEntity<?> createMockService(RequestEntity<MockServiceRequest> request) {
		MockServiceRequest req = request.getBody();
		
		logger.info("Request is : "+req);
		
		try {
			MockServiceRequest requestBody = request.getBody();
			mockServicesAdaptor.setResponse(encryptPathToStringKey(req.getPath()), requestBody);
			System.out.println("service created successfully");
			return getSuccessResponse("Service created Successfully");
		}catch(Exception e) {
			e.printStackTrace();
			return getParamError(e.getMessage());
		}
	}
		
//	private boolean ifEmpty(String str) {
//		return (str == null)||(str.isEmpty());
//	}
}
