package com.mockservices.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.req_resp.MockServiceRequest;

@RestController
@RequestMapping("/rest/delete")
public class MockServicesDelete extends MockServicesCommon {

	private static final Logger logger = LoggerFactory.getLogger(MockServicesDelete.class.getName());
	
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deleteMockService( RequestEntity<MockServiceRequest> request ) {
		logger.info("============= Got request to delete " + request.getBody().getPath() + " =============");
		try {
			String method = request.getBody().getHeaders().getMethod();
			mockServicesAdaptor.deleteService(encryptPathToStringKey(request.getBody().getPath()), method);
			return getSuccessResponse("Service Deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return getParamError("Not able to delete. Cause is : "+e.getMessage());
		}
	}
}
