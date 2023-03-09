package com.bankappback.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Application
 * 
 * @author mjpol
 *
 */
@SpringBootApplication(scanBasePackages = {"com.bankappback.*"})
@PropertySources({
    @PropertySource("classpath:application.properties")
})
@OpenAPIDefinition(info = @Info(title = "SimilarProducts", version = "1.0", description = ""))
public class Application {	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
