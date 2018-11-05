package org.playground.login.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
public class LoginPlaygroundApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LoginPlaygroundApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LoginPlaygroundApplication.class);
	}

}
