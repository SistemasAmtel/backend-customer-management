package pe.com.americatel.cm.client.http.execution;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class HttpURLConnectionExecution {
	private static Logger logger = Logger.getLogger(HttpURLConnectionExecution.class);

	private static final String HTTP_METHOD_TYPE_POST = "POST";
	private static final String HTTP_METHOD_TYPE_PUT = "PUT";

	public static Map<String, Object> clientRest(String httpMethodType, Map<String, Object> httpHeaders,
			String urlEndpoint, String httpRequestPayload) {

		Map<String, Object> results = new HashMap<String, Object>();

		Integer httpStatusCode = -100;
		String responsePayload = "{}";
		HttpURLConnection httpURLConnection;
		try {
			httpURLConnection = HttpURLConnectionExecution.execute(httpMethodType, httpHeaders, urlEndpoint,
					httpRequestPayload);
			httpStatusCode = httpURLConnection.getResponseCode();
			logger.info("httpStatusCode : " + httpStatusCode);

			responsePayload = IOUtils.toString(httpURLConnection.getInputStream(), "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		results.put("httpStatusCode", httpStatusCode);
		results.put("responsePayload", responsePayload);
		return results;
	}

	public static HttpURLConnection execute(String httpMethodType, Map<String, Object> httpHeaders, String urlEndpoint,
			String httpRequestPayload) throws Exception {

		logger.info("Start HttpURLConnectionExecution.execute()");
		logger.info("httpMethodType : " + httpMethodType);
		logger.info("urlEndpoint : " + urlEndpoint);
		logger.info("httpRequestPayload : " + httpRequestPayload);

		URL url = new URL(urlEndpoint);

		byte[] byteArray = prepareByteArray(httpRequestPayload);

		URLConnection connection = url.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
		setRequestPropertyOfHttpHeaders(httpURLConnection, httpHeaders, byteArray);

		httpURLConnection.setRequestMethod(httpMethodType);
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setUseCaches(false);

		if (HTTP_METHOD_TYPE_POST.equals(httpMethodType) || HTTP_METHOD_TYPE_PUT.equals(httpMethodType)) {
			OutputStream out = httpURLConnection.getOutputStream();
			out.write(byteArray);
			out.close();
		}

		logger.info("Start HttpURLConnectionExecution.execute()");
		return httpURLConnection;
	}

	private static byte[] prepareByteArray(String httpRequestPayload) throws IOException {

		if ((httpRequestPayload == null) || (httpRequestPayload.trim().length() == 0)) {
			httpRequestPayload = "";
		}

		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		byte[] buffer = new byte[httpRequestPayload.length()];
		buffer = httpRequestPayload.getBytes();
		bout.write(buffer);
		return bout.toByteArray();
	}

	private static void setRequestPropertyOfHttpHeaders(HttpURLConnection httpURLConnection,
			Map<String, Object> httpHeaders, byte[] byteArray) {

		httpURLConnection.setRequestProperty("Content-Length", String.valueOf(byteArray.length));
		for (Map.Entry<String, Object> header : httpHeaders.entrySet()) {
			String key = header.getKey();
			String value = (String) header.getValue();
			httpURLConnection.setRequestProperty(key, value);
		}
	}
}
