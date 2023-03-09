package com.bankappback.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateConfig
 * 
 * @author mjpol
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class RestTemplateConfig {
	
	@Value("${api.host.baseurl}")
	private String apiHost;
	
	@Value("${api.host.connectTimeout}")
	private long connectTimeout;
	
	@Value("${api.host.readTimeout}")
	private long readTimeout;

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
        		.rootUri(apiHost)
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

}
