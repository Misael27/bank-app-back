package com.bankappback.repository;

import java.util.List;
import java.util.Optional;

import com.bankappback.model.Account;

public interface IAccountRepository {

	void save(Account account);
	
	Optional<Account> findById(Long id);

	void delete(Account account);

	List<Account> findAll();

	Boolean existsByNumber(String number);

	Boolean existsById(Long id);

}
