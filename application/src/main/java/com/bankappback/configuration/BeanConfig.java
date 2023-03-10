package com.bankappback.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bankappback.repository.IAccountRepository;
import com.bankappback.repository.IClientRepository;
import com.bankappback.repository.IMovementRepository;
import com.bankappback.service.IAccountService;
import com.bankappback.service.IClientService;
import com.bankappback.service.IMovementService;
import com.bankappback.service.impl.AccountService;
import com.bankappback.service.impl.ClientService;
import com.bankappback.service.impl.MovementService;

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
    
    @Bean
    IAccountService accountService(final IAccountRepository accountRepository, final IClientRepository clientRepository) {
        return new AccountService(accountRepository, clientRepository);
    }
    
    @Bean
    IMovementService movementService(final IMovementRepository movementRepository, final IAccountRepository accountRepository) {
        return new MovementService(movementRepository, accountRepository);
    }
		
}
