package org.simulator.http.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientHelper {

	private static final String JSON = "application/json";

	private static final String SOAP = "text/xml";

	@Autowired
	private HttpClient httpClient;

	public String postSOAP(String url, String soapXML, ResponseHandler<String> handler)
			throws ClientProtocolException, IOException {
		return post(SOAP, url, soapXML, handler);
	}

	public String postJSON(String url, String soapXML, ResponseHandler<String> handler)
			throws ClientProtocolException, IOException {
		return post(JSON, url, soapXML, handler);
	}

	private String post(String contentType, String url, String soapXML,
			ResponseHandler<String> handler) throws ClientProtocolException, IOException {
		HttpPost postRequest = new HttpPost(url);
		StringEntity input = new StringEntity(soapXML);
		input.setContentType(contentType);
		postRequest.setEntity(input);
		return httpClient.execute(postRequest, handler);
	}

	public HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = HttpClients.createDefault();
		}
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

}
