package org.simulator;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static ApplicationContext ctx;

	public static Object getBean(Class<?> clazz) {
		return ctx.getBean(clazz);
	}

	public static void main(String[] args) {

		// System.setProperty("org.apache.commons.logging.LogFactory",
		// "org.apache.commons.logging.impl.LogFactoryImpl");

		ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
		
 
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	}
}
