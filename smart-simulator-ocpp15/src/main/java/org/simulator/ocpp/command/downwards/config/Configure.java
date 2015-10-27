package org.simulator.ocpp.command.downwards.config;

import org.simulator.ocpp.command.downwards.DataTransfer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {

	@Bean
	DataTransfer createDataTransfer() {
		return new DataTransfer();
	}
}
