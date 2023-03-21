package com.bankappback.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.bankappback.*")
@PropertySources({
    @PropertySource("classpath:infrastructure.properties")
})
@EnableJpaRepositories(basePackages = {"com.bankappback.repository.springdata"})
@Configuration
@EntityScan("com.bankappback.mapping")
public class InfrastructureConfiguration {
	
}