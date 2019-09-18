package com.mockservices.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockservices.req_resp.MockServiceRequest;

@RestController
@RequestMapping( path = "/getAllServices" )
public class MockServiceGetAll extends MockServicesCommon {

	@RequestMapping( produces =  { "application/json" })
	public List<MockServiceRequest> getAll() {
		return mockServicesAdaptor.getAllServices();
	}
	
}
