package com.bankappback.repository;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.bankappback.mapping.ClientTable;
import com.bankappback.model.Client;
import com.bankappback.repository.springdata.SpringDataClientRepository;

@Repository
public class ClientRepository implements IClientRepository {

	SpringDataClientRepository springDataClientRepository;
	ModelMapper mapper;
	
	public ClientRepository(SpringDataClientRepository springDataClientRepository) {
		this.springDataClientRepository = springDataClientRepository;
		this.mapper = new ModelMapper();
		this.mapper.getConfiguration().setSkipNullEnabled(true);
	}

	@Override
	public void save(Client client) {
		ClientTable clientTable = this.mapper.map(client, ClientTable.class);
		springDataClientRepository.save(clientTable);
		client.setId(clientTable.getId());
	}

	@Override
	public Boolean existsByPersonId(String personId) {
		return springDataClientRepository.existsByPersonId(personId);
	}

	@Override
	public Optional<Client> findById(Long id) {
		ClientTable result = springDataClientRepository.findById(id).orElse(null);
		Client client = result != null ? mapper.map(result, Client.class) : null;
		return Optional.of(client);
	}
	
}
