package com.bankappback.repository;

import java.util.List;
import java.util.Optional;

import com.bankappback.model.Client;

public interface IClientRepository {

	void save(Client client);
	
	Boolean existsByPersonId(String personId);
	
	Optional<Client> findById(Long id);

	void delete(Client client);

	List<Client> findAll();
	
}
