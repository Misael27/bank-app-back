package com.bankappback.repository;

import java.util.ArrayList;
import java.util.List;
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
		if (clientTable.getId() != null) { //is update
			clientTable.setAccounts(
					springDataClientRepository.findById(clientTable.getId()).get().getAccounts());
		}
		springDataClientRepository.save(clientTable);
		client.setId(clientTable.getId());
	}

	@Override
	public Boolean existsByPersonId(String personId) {
		return springDataClientRepository.existsByPersonId(personId);
	}
	
	@Override
	public Boolean existsById(Long id) {
		return springDataClientRepository.existsById(id);
	}

	@Override
	public Optional<Client> findById(Long id) {
		ClientTable result = springDataClientRepository.findById(id).orElse(null);
		Client client = result != null ? mapper.map(result, Client.class) : null;
		return Optional.ofNullable(client);
	}
	
	@Override
	public List<Client> findAll() {
		var result = new ArrayList<Client>();
		springDataClientRepository.findAll().forEach(clienTable -> {
			result.add(mapper.map(clienTable, Client.class));
		});
		return result;
	}

	@Override
	public void delete(Client client) {
		springDataClientRepository.deleteById(client.getId());
	}
	
}
