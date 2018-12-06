package pe.com.americatel.cm.platform.jersey.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import pe.com.americatel.cm.client.http.execution.HttpURLConnectionExecution;
import pe.com.americatel.cm.platform.jersey.JerseyRestCustomer;
import pe.com.americatel.cm.platform.util.UtilRestCommons;

@Path("/endpoint/customer")
public class JerseyRestCustomerImpl implements JerseyRestCustomer {

	private Logger logger = Logger.getLogger(JerseyRestCustomerImpl.class);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/resources/post/customerByAccountManager")
	public Response resourcesPostCustomerByAccountManager(String requestPayload) {
		logger.info("Start resourcesPostCustomerByAccountManager");
		Long startTime = System.currentTimeMillis();

		logger.info("requestPayload : " + requestPayload);

		Response response = null;

		String httpMethod = "POST";
		//String urlEndpoint = "http://190.187.252.105/backend-sig-app/api/endpoint/customer/resources/post/customerByAccountManager";
		String urlEndpoint = "http://190.187.252.105/backend-sig-app/api/endpoint/customer/resources/post/customerByAccountManager;
					
		Map<String, Object> httpHeaders = new HashMap<String, Object>();
		httpHeaders.put("Content-Type", "application/json");

		Map<String, Object> results = HttpURLConnectionExecution.clientRest(httpMethod, httpHeaders, urlEndpoint,
				requestPayload);
		Integer httpStatusCode = (Integer) results.get("httpStatusCode");
		String responsePayload = (String) results.get("responsePayload");

		response = UtilRestCommons.createInstanceResponse(httpStatusCode, responsePayload);
		HashMap<String, String> elapsedTimeMap = UtilRestCommons.getElapsedTimeInMap(startTime);
		logger.info("elapsedTimeMap : " + elapsedTimeMap.toString());
		logger.info("Finish resourcesPostCustomerByAccountManager");
		return response;
	}

}
