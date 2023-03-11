package com.bankappback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.bankappback.mapping.AccountTable;
import com.bankappback.model.Account;
import com.bankappback.repository.springdata.SpringDataAccountRepository;

@Repository
public class AccountRepository implements IAccountRepository {

	SpringDataAccountRepository springDataAccountRepository;
	ModelMapper mapper;
	
	public AccountRepository(SpringDataAccountRepository springDataAccountRepository) {
		this.springDataAccountRepository = springDataAccountRepository;
		this.mapper = new ModelMapper();
		this.mapper.getConfiguration().setSkipNullEnabled(true);
	}

	@Override
	public void save(Account account) {
		AccountTable accountTable = this.mapper.map(account, AccountTable.class);
		if (accountTable.getId() != null) { //is update
			accountTable.setMovements(
					springDataAccountRepository.findById(accountTable.getId()).get().getMovements());
		}
		springDataAccountRepository.save(accountTable);
		account.setId(accountTable.getId());
	}

	@Override
	public Boolean existsByNumber(String number) {
		return springDataAccountRepository.existsByNumber(number);
	}

	@Override
	public Optional<Account> findById(Long id) {
		AccountTable result = springDataAccountRepository.findById(id).orElse(null);
		Account account = result != null ? mapper.map(result, Account.class) : null;
		return Optional.ofNullable(account);
	}
	
	@Override
	public List<Account> findAll() {
		var result = new ArrayList<Account>();
		springDataAccountRepository.findAll().forEach(clienTable -> {
			result.add(mapper.map(clienTable, Account.class));
		});
		return result;
	}

	@Override
	public void delete(Account account) {
		springDataAccountRepository.deleteById(account.getId());
	}

	@Override
	public Boolean existsById(Long id) {
		return springDataAccountRepository.existsById(id);
	}
	
}
