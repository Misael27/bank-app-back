package com.bankappback.service.impl;

import com.bankappback.exception.ResourceBadRequestException;
import com.bankappback.model.Client;
import com.bankappback.repository.IClientRepository;
import com.bankappback.service.IClientService;

public class ClientService implements IClientService{

	IClientRepository clientRepository;
	
	public ClientService(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@Override
	public void create(Client client) {
		if(!client.isValid()) {
			throw new ResourceBadRequestException("INVALID_REQUEST");
		}
		client.encryptPassword();
		clientRepository.save(client);
	}

}
