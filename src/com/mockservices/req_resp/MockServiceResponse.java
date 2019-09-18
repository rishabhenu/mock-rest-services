package com.mockservices.req_resp;

public class MockServiceResponse {

	private String status;
	private String statusText;
	private Object service;

	public Object getService() {
		return service;
	}

	public void setService(Object service) {
		this.service = service;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
	public static MockServiceResponse getSuccessfulInstance(String statusText, Object service) {
		MockServiceResponse response = new MockServiceResponse();
		response.setStatus("Success");
		response.setStatusText(statusText);
		response.setService(service);
		return response;
	}
	public static MockServiceResponse getFailuireInstance(String statusText, Object service) {
		MockServiceResponse response = new MockServiceResponse();
		response.setStatus("Failure");
		response.setStatusText(statusText);
		response.setService(service);
		return response;
	}
}
