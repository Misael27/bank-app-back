package com.bankappback.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bankappback.repository.IClientRepository;
import com.bankappback.service.IClientService;
import com.bankappback.service.impl.ClientService;

/**
 * Application
 * 
 * @author mjpol
 *
 */
@Configuration
public class BeanConfig {	
	
    @Bean
    IClientService clientService(final IClientRepository clientRepository) {
        return new ClientService(clientRepository);
    }
		
	
}
