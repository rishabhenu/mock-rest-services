package com.externalpricingservices.resources;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.externalpricingservices.services.RmsPricingServiceAdaptor;
import com.externalpricingservices.services.requests.RmsPricingRequest;
import com.externalpricingservices.services.responses.RmsAuthenticationResponse;
import com.externalpricingservices.services.responses.RmsFailureResponse;
import com.externalpricingservices.services.responses.RmsPricingResponse;

@Path("rms")
public class RMSPricingService {

	private static final Logger logger = Logger.getLogger(RMSPricingService.class.getName());
	private RmsPricingServiceAdaptor rmsPricingAdaptor;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pricing/{(.*)}")
	public Object getRmsPrices(RmsPricingRequest req, @PathParam(value = "(.*)") String vin) {
		logger.info("-----------------------------------------------------------------------------------------------");
		logger.info("Got pricing request for vin : "+vin);
		RmsPricingResponse resp = rmsPricingAdaptor.getRmsPriceResponse(req, vin);
		if(resp != null) {
			logger.info("RmsPricingRequest is completed successfully for vin : "+vin+". Response is "+resp);
			logger.info("----------------------------------------------------------------------------------------------");
			return resp;
		}
		logger.severe("RmsPricingResponse is null");
		return null;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/auth")
	public RmsAuthenticationResponse getAuthenticationResponse() {
		logger.info("-----------------------------------------------------------------------------------------------");
		logger.info("Got authentication request");
		RmsAuthenticationResponse resp = rmsPricingAdaptor.getAuthenticationRes();
		logger.info("RmsAuthentication request is processed successfully. Response is "+resp);
		logger.info("----------------------------------------------------------------------------------------------");
		return resp;
	}

	public void setRmsPricingServiceAdaptor(RmsPricingServiceAdaptor rmsPricingAdaptor) {
		this.rmsPricingAdaptor = rmsPricingAdaptor;
	}

}
