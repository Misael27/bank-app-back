package com.bankappback.service.impl;

import com.bankappback.exception.ResourceBadRequestException;
import com.bankappback.exception.ResourceNotFoundException;
import com.bankappback.model.Client;
import com.bankappback.repository.IClientRepository;
import com.bankappback.service.IClientService;

public class ClientService implements IClientService {

	IClientRepository clientRepository;
	
	public ClientService(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@Override
	public void create(Client client) {
		if(!client.isValid()) {
			throw new ResourceBadRequestException("INVALID_REQUEST");
		}
		if (clientRepository.existsByPersonId(client.getPersonId())) {
			throw new ResourceBadRequestException("PERSON_ID_ALREADY_EXIST");
		}
		client.encryptPassword();
		client.setId(null);
		clientRepository.save(client);
	}

	@Override
	public Client update(Long clientId, Client clientUpdate) {
		Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("ClientId "+clientId+" not found", "CLIENT_NOT_FOUND"));
		if (!clientUpdate.getPersonId().equals(client.getPersonId()) && clientRepository.existsByPersonId(clientUpdate.getPersonId())) {
			throw new ResourceBadRequestException("PERSON_ID_ALREADY_EXIST");
		}
		client.update(clientUpdate);
		if(!client.isValid()) {
			throw new ResourceBadRequestException("INVALID_REQUEST");
		}
		clientRepository.save(client);
		return client;
	}

}
