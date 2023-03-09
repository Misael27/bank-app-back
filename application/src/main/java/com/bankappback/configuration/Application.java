package com.bankappback.configuration;

import com.bankappback.repository.IProductRepository;
import com.bankappback.service.impl.ProductService;
import com.bankappback.service.IProductService;
import com.bankappback.service.impl.SimilarService;
import com.bankappback.service.ISimilarService;

import org.springframework.context.annotation.Bean;
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
	
    @Bean
    IProductService productService(final IProductRepository productRepository) {
        return new ProductService(productRepository);
    }
	
	@Bean
    ISimilarService similarService(final IProductService productService) {
        return new SimilarService(productService);
    }
	
	
}
