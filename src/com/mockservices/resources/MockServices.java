package com.mockservices.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.mockservices.services.MockServicesAdaptor;

@Component
@Path("/mock/service")
public class MockServices {

	private static final Logger logger = Logger.getLogger(MockServices.class.getName());
	private MockServicesAdaptor mockServicesAdaptor;

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	@Path("/create/{url}")
	public String createNewResponse(String req, @PathParam("url") String url) {
		logger.info("-----------------------Received request to create /" + url
				+ " service---------------------------------");
		logger.info("Request : " + req);
		String resp;
		if (mockServicesAdaptor.setResponse(url, req))
			resp = "{\"status\" : \"success\"}";
		else
			resp = "{\"status\" : \"failure\"}";
		logger.info("-------------------------------------------------------------------------------------");
		return resp;
	}

	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	@Path("/generate/{url}")
	public String getRmsPrices(String req, @PathParam("url") String url) {
		
		logger.info("-----------------------Received request to generate /" + url
				+ " service---------------------------------");
		String resp = null;
		if (mockServicesAdaptor.getResponse(url) != null) {
			resp = (String) mockServicesAdaptor.getResponse(url);
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
			return resp;
		} else {
			resp = "{\"status\":\"failure\",\"status-message\":\"This service is not created yet please create first\"}";
			logger.info("Response is : " + resp);
			logger.info("-----------------------Finished /" + url + " service---------------------------------");
			return resp;
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	@Path("/get-all/services")
	public Object getAllServices(){
		Map<String,Object> map = new HashMap<>();
		if(mockServicesAdaptor.getAllServices() == null || mockServicesAdaptor.getAllServices().size()==0) {
			map.put("status", "success");
			map.put("message", "No any service created as of now");
		}else {
			List<Map<String,Object>> services = new ArrayList<>();
			int index = 1;
			for(String s : mockServicesAdaptor.getAllServices()) {
				Map<String,Object> tempMap = new HashMap<String, Object>();
				tempMap.put(index+"", s);
				services.add(tempMap);
				index++;
			}
			map.put("services", services);
			map.put("status", "success");
			return map;
		}
		return map;
	}

	public void setMockServicesAdaptor(MockServicesAdaptor mockServicesAdaptor) {
		this.mockServicesAdaptor = mockServicesAdaptor;
	}

}
