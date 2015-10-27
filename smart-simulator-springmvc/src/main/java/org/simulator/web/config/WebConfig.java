package org.simulator.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
// @ComponentScan can only scan the Configuration in this project, but can not scan the project it depends
@ComponentScan(basePackages = "org.simulator")
public class WebConfig extends WebMvcConfigurerAdapter {

	// @Override
	// public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	// configurer.favorPathExtension(false).favorParameter(true);
	// }
	//

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}