package com.mockservices.resources;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.req_resp.MockServiceRequest;
import com.mockservices.req_resp.MockServiceRequestAdvancedOptions;

@RestController
@CrossOrigin(value = { "*" })
@RequestMapping(path = "/rest/generate")
public class MockServicesGenerate extends MockServicesCommon {

	@RequestMapping(path = { "/{param1}" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<?> getMockResponse(	RequestEntity<?> request, 
													@PathVariable("param1") String path) {
		return getMockResponseCommon(request, new String[] {path});
	}

	@RequestMapping(path = { "/{param1}/{param2}" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<?> getMockResponse(	RequestEntity<?> request, 
													@PathVariable("param1")String param1,
													@PathVariable("param2")String param2) {
		return getMockResponseCommon(request, new String[] {param1, param2});
	}
	
	@RequestMapping(path = { "/{param1}/{param2}/{param3}" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<?> getMockResponse(	RequestEntity<?> request, 
													@PathVariable("param1")String param1, 
													@PathVariable("param2")String param2,
													@PathVariable("param3")String param3) {
		return getMockResponseCommon(request, new String[] {param1, param2, param3});
	}

	
	private ResponseEntity<?> getMockResponseCommon(RequestEntity<?> request, String[] pathParams) {
		try {
			String method = request.getMethod().toString().toUpperCase();
			
			// take actual service from map
			MockServiceRequest pathRequest = mockServicesAdaptor.getResponse(decryptStringKeyToPath(pathParams, false), method);
			
			// if actual url path is not available, check if user had created any variable path at the end.
			if(pathRequest == null) {
				pathRequest = mockServicesAdaptor.getResponse(decryptStringKeyToPath(pathParams, true), method);
			}
			
			if(pathRequest == null) {
				return getParamError("This service is not yet created");
			}
			
			MockServiceRequestAdvancedOptions reqAdvance = pathRequest.getAdvancedOptions();
			// this is for delay, by default time delay is 0.
			Thread.sleep(reqAdvance.getTimeDelay());

			// this process is for headers.
			MultiValueMap<String, String> headers = new HttpHeaders();
			
			HttpStatus httpStatus = HttpStatus.valueOf(reqAdvance.getResponseCode());
			// build complete response using body, headers and status
			return new ResponseEntity<String>(pathRequest.getBody(), headers, httpStatus);
		} catch (Exception e) {
			e.printStackTrace();
			return getParamError(e.getMessage());
		}
	}

}
