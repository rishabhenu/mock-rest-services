package com.externalpricingservices.services;

import com.externalpricingservices.services.requests.RmsPricingRequest;
import com.externalpricingservices.services.responses.RmsAuthenticationResponse;
import com.externalpricingservices.services.responses.RmsPricingResponse;

public class RmsPricingServiceAdaptor {
	
	public RmsPricingResponse getRmsPriceResponse(RmsPricingRequest req, String vin) {
		RmsPricingResponse resp = new RmsPricingResponse();
		resp.setCurrency("USD");
		resp.setCreatedAt("2019-05-20T13:31:24.1347932Z");
		resp.setPriceType("RMR");
		resp.setValue(35000d);
		return resp;
	}
	
	public RmsAuthenticationResponse getAuthenticationRes() {
		RmsAuthenticationResponse resp = new RmsAuthenticationResponse();
		resp.setAccess_token("780a5cb46bd544ba9ee873e9e1771bb");
		resp.setExpires("2020-08-05T16:47:10.4373939Z");
		resp.setIssued("2019-08-05T16:47:10.8964399Z");
		resp.setRefresh_token("265f66e419c84ebba2ca0078aeb986a");
		return resp;
	}

}
