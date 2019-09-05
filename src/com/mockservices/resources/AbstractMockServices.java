package com.mockservices.resources;

public abstract class AbstractMockServices {

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
}
