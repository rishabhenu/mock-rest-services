package com.mockservices.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mockservices.req_resp.MockServiceInfoResponse;
import com.mockservices.resources.MockServicesCommon.Constants;
import com.mockservices.services.MockServicesAdaptor;

public class MockServicesCommon {

	@Autowired
	@Qualifier("mockServicesAdaptor")
	protected MockServicesAdaptor mockServicesAdaptor;
	
	protected String encryptPathToStringKey(String path) {
		StringBuilder requestPath = new StringBuilder(path);
		if(requestPath.charAt(requestPath.length()-1) == '*') {
			requestPath.replace(requestPath.length()-1, requestPath.length(), Constants.REPLACEMENT_OF_VARIABLE_PATH_IN_THE_END);
		}
		return requestPath.toString();
	}
	
	protected String decryptStringKeyToPath(String[] pathParams, boolean isLastParamVariable) {
		StringBuilder path = new StringBuilder();
		for(int i=0; i<pathParams.length-1; i++) {
			path.append(pathParams[i]).append(Constants.CONNECTING_TWO_PATHS);
		}
		path.append(isLastParamVariable?Constants.REPLACEMENT_OF_VARIABLE_PATH_IN_THE_END:pathParams[pathParams.length-1]);
		return path.toString();
	}
	
	protected String getContentType(String resp) {
		if (resp != null && !resp.isEmpty()) {
			if (resp.trim().charAt(0) == '{' && resp.trim().charAt(resp.trim().length() - 1) == '}') {
				return Constants.CONTENT_TYPE.JSON.stringValue();
			} else if (resp.trim().charAt(0) == '<' && resp.trim().charAt(resp.trim().length() - 1) == '>') {
				return Constants.CONTENT_TYPE.XML.stringValue();
			}
		}
		return Constants.CONTENT_TYPE.TEXT_PLAIN.stringValue();
	}
	
	protected static interface Constants {
		public final String CONNECTING_TWO_PATHS = "/";
		public final String REPLACEMENT_OF_VARIABLE_PATH_IN_THE_END = "#ANYTHING#";
		static enum CONTENT_TYPE {
			JSON("application/json"),
			XML("application/xml"),
			TEXT_PLAIN("text/plain");
			
			private String contentType;
			private CONTENT_TYPE(String contentType) {
				this.contentType = contentType;
			}
			String stringValue() {
				return contentType;
			}
		}
		static enum HTTP_METHOD_TYPE_STRING {
			GET("GET"),
			POST("POST");
			
			private String method;
			private HTTP_METHOD_TYPE_STRING(String method) {
				this.method = method;
			}
			String stringValue() {
				return method;
			}
		}
	}
	
	protected ResponseEntity<?> getParamError(String statusText) {
		return new ResponseEntity<MockServiceInfoResponse>(new MockServiceInfoResponse("Failure", statusText), HttpStatus.OK);
	}
	
	protected ResponseEntity<?> getSuccessResponse(String statusText) {
		return new ResponseEntity<MockServiceInfoResponse>(new MockServiceInfoResponse("Success", statusText), HttpStatus.OK);
	}

}
