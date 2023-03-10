package com.bankappback.repository.springdata;

import org.springframework.data.repository.CrudRepository;

import com.bankappback.mapping.AccountTable;

public interface SpringDataAccountRepository extends CrudRepository<AccountTable, Long> {

	Boolean existsByNumber(String personId);
	
}