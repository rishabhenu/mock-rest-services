package com.externalpricingservices.services.responses;

public class RmsAuthenticationResponse {
	
	private String access_token;
	private String expires;
	private String issued;
	private String refresh_token;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getIssued() {
		return issued;
	}

	public void setIssued(String issued) {
		this.issued = issued;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	@Override
	public String toString() {
		return "RmsAuthenticationResponse [access_token=" + access_token + ", expires=" + expires + ", issued=" + issued
				+ ", refresh_token=" + refresh_token + "]";
	}
}
