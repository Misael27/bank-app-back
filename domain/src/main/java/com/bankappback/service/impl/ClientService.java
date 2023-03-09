package com.bankappback.service.impl;

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
			return;
		}
		clientRepository.save(client);
	}

}
