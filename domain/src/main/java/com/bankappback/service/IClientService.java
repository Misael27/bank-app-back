package com.bankappback.service;

import java.util.List;

import com.bankappback.model.Client;

public interface IClientService {

	/**
	 * Create new client
	 * @param client
	 */
	void create(Client client);

	Client update(Long clientId, Client clientUpdate);

	Client findById(Long clientId);

	void delete(Long clientId);

	List<Client> findAll();
	
}
