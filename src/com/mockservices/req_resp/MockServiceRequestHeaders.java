package com.mockservices.req_resp;

public class MockServiceRequestHeaders {
	
	private String contentType;
	private String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "MockServiceRequestHeaders [contentType=" + contentType + ", method=" + method + "]";
	}

}
