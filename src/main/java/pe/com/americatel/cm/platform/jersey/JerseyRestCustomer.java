package pe.com.americatel.cm.platform.jersey;

import javax.ws.rs.core.Response;

public interface JerseyRestCustomer {

	Response resourcesPostCustomerByAccountManager(String requestPayload);

}
