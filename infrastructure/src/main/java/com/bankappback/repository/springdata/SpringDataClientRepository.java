package com.bankappback.repository.springdata;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bankappback.mapping.ClientTable;

public interface SpringDataClientRepository extends CrudRepository<ClientTable, String> {

	Boolean existsByPersonId(String personId);

	Optional<ClientTable> findById(Long id);

}
