package com.bankappback.repository;

import java.util.Optional;

import com.bankappback.model.Client;

public interface IClientRepository {

	void save(Client client);
	
	Boolean existsByPersonId(String personId);
	
	Optional<Client> findById(Long id);
	
}
