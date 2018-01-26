package pe.com.americatel.cm.base.http.client.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import pe.com.americatel.cm.client.http.execution.HttpURLConnectionExecution;

public class HttpURLConnectionExecutionTest {
	private Logger logger = Logger.getLogger(HttpURLConnectionExecutionTest.class);

	@Test
	public void consumeClientHttp() {
		logger.info("Start consumeClientHttp");

		String httpMethod = "POST";
		String urlEndpoint = "http://190.187.252.105:8501/customer-management-platform/api/endpoint/customer/resources/post/customerByAccountManager";
		String requestPayload = "{\"apiRequest\": {\"resourceName\": \"customerByAccountManager\",\"apiRequestPayload\": {\"documentType\": \"RUC\",\"documentNumber\": \"20101266819\"}}}";
		Map<String, Object> httpHeaders = new HashMap<String, Object>();
		httpHeaders.put("Content-Type", "application/json");
		Map<String, Object> consumeClientResult = HttpURLConnectionExecution.clientRest(httpMethod, httpHeaders, urlEndpoint,
				requestPayload);

		logger.info("consumeClientResult : " + consumeClientResult);

		logger.info("End consumeClientHttp");

	}
}