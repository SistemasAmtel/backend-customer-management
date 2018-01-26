package pe.com.americatel.cm.platform.util;

import java.util.HashMap;

import javax.ws.rs.core.Response;

public class UtilRestCommons {

	private UtilRestCommons() {

	}

	public static Response createInstanceResponse(Integer status, String responsePayload) {

		return Response.status(status).entity(responsePayload).build();

	}

	public static HashMap<String, String> getElapsedTimeInMap(Long startTime) {

		HashMap<String, String> mapResult = new HashMap<String, String>();

		Long endTime = System.currentTimeMillis();
		Long elapsedTimeMillis = endTime - startTime;

		Float elapsedTimeSec = elapsedTimeMillis / 1000F;
		Float elapsedTimeMin = elapsedTimeMillis / (60 * 1000F);

		String printMessage = "Elapsed time of the execution in milliseconds " + elapsedTimeMillis + " | in seconds "
				+ elapsedTimeSec + " | in minutes " + elapsedTimeMin;

		mapResult.put("elapsedTimeMillis", elapsedTimeMillis.toString());
		mapResult.put("elapsedTimeSec", elapsedTimeSec.toString());
		mapResult.put("elapsedTimeMin", elapsedTimeMin.toString());
		mapResult.put("printMessage", printMessage);
		return mapResult;
	}
}
