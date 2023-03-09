package com.bankappback.service;

import com.bankappback.model.Client;

public interface IClientService {

	/**
	 * Create new client
	 * @param client
	 */
	void create(Client client);

	Client update(Long clientId, Client clientUpdate);
	
}
