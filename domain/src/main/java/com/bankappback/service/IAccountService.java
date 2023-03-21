package com.bankappback.service;

import java.util.List;

import com.bankappback.model.Account;


public interface IAccountService {

	void create(Account account);

	Account findById(Long accountId);

	List<Account> findAll();

	Account update(Long accountId, Account account);

	void delete(Long accountId);

}
