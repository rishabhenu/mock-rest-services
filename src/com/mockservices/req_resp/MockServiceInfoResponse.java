package com.mockservices.req_resp;

public class MockServiceInfoResponse {

	@Override
	public String toString() {
		return "MockServiceInfoResponse [status=" + status + ", statusText=" + statusText + "]";
	}

	private String status;
	private String statusText;
	
	public MockServiceInfoResponse(String status, String statusText) {
		this.status = status;
		this.statusText = statusText;
	}
	
	public MockServiceInfoResponse() {}
	
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

}
