package com.mockservices.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.services.MockServicesAdaptor;

@RestController
@RequestMapping(path = "/rest/generate")
public class MockServicesGenerate extends AbstractMockServices {

	private static final Logger logger = LoggerFactory.getLogger(MockServicesGenerate.class.getName());
	
	@Autowired
	private MockServicesAdaptor mockServicesAdaptor;

//	@RequestMapping(
//				consumes = { "application/json", "application/xml", "text/plain" }, 
//				method = RequestMethod.POST, 
//				path = {"/{url}", "/{url}/{vin}"}
//			)
//	public ResponseEntity<String> getMockResponsePOST(@RequestBody String req, @PathVariable(value = "url") String url,
//			HttpServletRequest request, HttpServletResponse response) {
//		
//		String method = request.getMethod().toUpperCase();
//		logger.info("-----------------------Received request to generate /" + url + " service. Method : " + method
//				+ " ---------------------------------");
//		String resp = null;
//		if (mockServicesAdaptor.getResponse(url, request.getMethod().toUpperCase()) != null) {
//			resp = (String) mockServicesAdaptor.getResponse(url, method);
//			logger.info("Response is : " + resp);
//			logger.info("-----------------------Finished /" + url + " service---------------------------------");
//		} else {
//			resp = "{\"status\":\"failure\",\"status-message\":\"This service is not created yet please create first\"}";
//			logger.info("Response is : " + resp);
//			logger.info("-----------------------Finished /" + url + " service---------------------------------");
//		}
//		
//		return new ResponseEntity<String>(resp, setContentType(resp), HttpStatus.OK);
//	}

//	@RequestMapping(
//				consumes = { "application/json", "application/xml", "text/plain" }, 
//				method = RequestMethod.POST, 
//				path = "/{url}/{vin}"
//			)
//	public ResponseEntity<String> getResponseWithTwoParamsPOST(@RequestBody String req, @PathVariable(value = "url") String url,
//			HttpServletRequest request, HttpServletResponse response) {
//		return getMockResponsePOST(req, url, request, response);
//	}

	@RequestMapping(
				consumes = { "application/json", "application/xml", "text/plain", "text/html;charset=UTF-8" }, 
				produces = { "application/json", "application/xml", "text/plain", "text/html;charset=UTF-8" },
				method = {RequestMethod.POST, RequestMethod.GET}, 
				path = {"/{url}", "/{url}/{vin}"}
			)
	public @ResponseBody ResponseEntity<Object> getMockResponse(RequestEntity<String> request, @PathVariable(value = "url") String url) {
		
		String method = request.getMethod().toString().toUpperCase();
		logger.info("-----------------------Received request to generate /" + url + " service. Method : " + method
				+ " ---------------------------------");
		String resp = null;
		if (mockServicesAdaptor.getResponse(url, method) != null) {
			resp = (String) mockServicesAdaptor.getResponse(url, method);
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
		} else {
			resp = "{\"status\":\"failure\",\"status-message\":\"This service is not created yet please create first\"}";
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
		}
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", getContentType(resp));
		return new ResponseEntity<Object>(resp, headers, HttpStatus.OK);
	}

//	@RequestMapping(
//				method = RequestMethod.GET, 
//				path = "/{url}/{vin}"
//			)
//	public ResponseEntity<String> getResponseWithTwoParamsGET(@PathVariable(value = "url") String url,
//			HttpServletRequest request, HttpServletResponse response) {
//		return getMockResponseGET(url, request, response);
//	}
	
//	private MultiValueMap<String,String> setContentType(String resp) {
//		MultiValueMap<String, String> headers = new HttpHeaders();
//		if(resp != null && !resp.isEmpty()) {
//			if(resp.trim().charAt(0) == '{' && resp.trim().charAt(resp.trim().length()-1) == '}') {
//				headers.add("Content-Type", "application/json");
//			}else if(resp.trim().charAt(0) == '<' && resp.trim().charAt(resp.trim().length()-1) == '>') {
//				headers.add("Content-Type", "application/xml");
//			}
//		}
//		return headers;
//	}
}
