package com.bankappback.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bankappback")
@PropertySources({
    @PropertySource("classpath:application.properties")
})
@Configuration
public class InfrastructureConfiguration {


	
}