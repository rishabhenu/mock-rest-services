package com.mockservices.req_resp;

public class MockServiceRequestAdvancedOptions {
	private Long timeDelay;
	private Integer responseCode = 200;

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public Long getTimeDelay() {
		return timeDelay;
	}

	public void setTimeDelay(Long timeDelay) {
		this.timeDelay = timeDelay;
	}

	@Override
	public String toString() {
		return "AdvancedOptions [timeDelay=" + timeDelay + ", responseCode=" + responseCode + "]";
	}
}