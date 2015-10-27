package org.simulator.message.service.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	HttpClient createHttpClient() {
		return HttpClients.createDefault();
	}

}
