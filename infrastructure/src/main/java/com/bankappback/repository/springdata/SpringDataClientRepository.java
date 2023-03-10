package com.bankappback.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import com.bankappback.mapping.ClientTable;

public interface SpringDataClientRepository extends CrudRepository<ClientTable, Long> {

	Boolean existsByPersonId(String personId);
	
}
