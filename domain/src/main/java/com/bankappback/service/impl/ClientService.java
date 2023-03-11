package com.bankappback.service.impl;

import java.util.List;
import java.util.Objects;

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
		validatePreCreate(client);
		client.encryptPassword();
		clientRepository.save(client);
	}

	@Override
	public Client findById(Long clientId) {
		return clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("ClientId "+clientId+" not found", "CLIENT_NOT_FOUND"));
	}
	
	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client update(Long clientId, Client clientUpdate) {
		final Client client = findById(clientId);
		validatePreUpdate(client, clientUpdate);
		clientRepository.save(client);
		return client;
	}

	@Override
	public void delete(Long clientId) {
		final Client client = findById(clientId);
		clientRepository.delete(client);
	}

	private void validatePreCreate(Client client) {
		if(!client.isValid()) {
			throw new ResourceBadRequestException("INVALID_REQUEST");
		}
		if (clientRepository.existsByPersonId(client.getPersonId())) {
			throw new ResourceBadRequestException("PERSON_ID_ALREADY_EXIST");
		}
	}
	
	private void validatePreUpdate(Client client, Client clientUpdate) {
		if (!Objects.isNull(clientUpdate.getPersonId()) && !clientUpdate.getPersonId().equals(client.getPersonId()) && clientRepository.existsByPersonId(clientUpdate.getPersonId())) {
			throw new ResourceBadRequestException("PERSON_ID_ALREADY_EXIST");
		}
		client.update(clientUpdate);
		if(!client.isValid()) {
			throw new ResourceBadRequestException("INVALID_REQUEST");
		}
	}

}
