package com.bankappback.service.impl;

import java.util.List;

import com.bankappback.exception.ResourceBadRequestException;
import com.bankappback.exception.ResourceNotFoundException;
import com.bankappback.model.Account;
import com.bankappback.repository.IAccountRepository;
import com.bankappback.repository.IClientRepository;
import com.bankappback.service.IAccountService;

public class AccountService implements IAccountService {

	IAccountRepository accountRepository;
	IClientRepository clientRepository;
	
	public AccountService(IAccountRepository accountRepository, IClientRepository clientRepository) {
		this.accountRepository = accountRepository;
		this.clientRepository = clientRepository;
	}
	
	@Override
	public void create(Account account) {
		validatePreCreate(account);
		account.setId(null);
		accountRepository.save(account);
	}

	@Override
	public Account findById(Long accountId) {
		return accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("AccountId "+accountId+" not found", "ACCOUNT_NOT_FOUND"));
	}
	
	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account update(Long accountId, Account accountUpdate) {
		final Account account = findById(accountId);
		validatePreUpdate(account, accountUpdate);
		accountRepository.save(account);
		return account;
	}

	@Override
	public void delete(Long accountId) {
		final Account account = findById(accountId);		
		accountRepository.delete(account);
	}
	
	private void validatePreCreate(Account account) {
		if(!account.isValid()) {
			throw new ResourceBadRequestException("INVALID_REQUEST");
		}
		if (accountRepository.existsByNumber(account.getNumber())) {
			throw new ResourceBadRequestException("ACCOUNT_NUMBER_ALREADY_EXIST");
		}
		if(!clientRepository.existsById(account.getClient().getId())) {
			throw new ResourceNotFoundException("ClientId "+account.getClient().getId()+" not found", "CLIENT_NOT_FOUND");
		}
	}
	
	private void validatePreUpdate(Account account, Account accountUpdate) {
		if (!accountUpdate.getNumber().equals(account.getNumber()) && accountRepository.existsByNumber(accountUpdate.getNumber())) {
			throw new ResourceBadRequestException("ACCOUNT_NUMBER_ALREADY_EXIST");
		}
		account.update(accountUpdate);
		if(!account.isValid()) {
			throw new ResourceBadRequestException("INVALID_REQUEST");
		}
		if(accountUpdate.getClient() != null && !clientRepository.existsById(accountUpdate.getClient().getId())) {
			throw new ResourceNotFoundException("ClientId "+account.getClient().getId()+" not found", "CLIENT_NOT_FOUND");
		}
	}

}
