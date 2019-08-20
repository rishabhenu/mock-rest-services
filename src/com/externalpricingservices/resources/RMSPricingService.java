package com.externalpricingservices.resources;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.externalpricingservices.services.RmsPricingServiceAdaptor;
import com.externalpricingservices.services.responses.RmsAuthenticationResponse;

@Path("/mock/service")
public class RMSPricingService {

	private static final Logger logger = Logger.getLogger(RMSPricingService.class.getName());
	private RmsPricingServiceAdaptor rmsPricingAdaptor;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create/{url}")
	public String createNewResponse(String req, @PathParam("url") String url) {
		logger.info("-----------------------Received request to create /" + url
				+ " service---------------------------------");
		logger.info("Request : " + req);
		String resp;
		if (rmsPricingAdaptor.setResponse(url, req))
			resp = "{\"status\" : \"success\"}";
		else
			resp = "{\"status\" : \"failure\"}";
		logger.info("-------------------------------------------------------------------------------------");
		return resp;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/generate/{url}")
	public String getRmsPrices(String req, @PathParam("url") String url) {
		logger.info("-----------------------Received request to generate /" + url
				+ " service---------------------------------");
		String resp = null;
		if (rmsPricingAdaptor.getResponse(url) != null) {
			resp = (String) rmsPricingAdaptor.getResponse(url);
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/auth")
	public RmsAuthenticationResponse getAuthenticationResponse() {
		logger.info("-----------------------------------------------------------------------------------------------");
		logger.info("Got authentication request");
		RmsAuthenticationResponse resp = rmsPricingAdaptor.getAuthenticationRes();
		logger.info("RmsAuthentication request is processed successfully. Response is " + resp);
		logger.info("----------------------------------------------------------------------------------------------");
		return resp;
	}

	public void setRmsPricingServiceAdaptor(RmsPricingServiceAdaptor rmsPricingAdaptor) {
		this.rmsPricingAdaptor = rmsPricingAdaptor;
	}

}
