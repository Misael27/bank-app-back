package com.bankappback.service.impl;

import java.util.List;

import com.bankappback.exception.ResourceBadRequestException;
import com.bankappback.exception.ResourceNotFoundException;
import com.bankappback.model.Account;
import com.bankappback.model.EMovementType;
import com.bankappback.model.Movement;
import com.bankappback.repository.IMovementRepository;
import com.bankappback.repository.IAccountRepository;
import com.bankappback.service.IMovementService;

public class MovementService implements IMovementService {

	IMovementRepository movementRepository;
	IAccountRepository accountRepository;
	
	public MovementService(IMovementRepository movementRepository, IAccountRepository accountRepository) {
		this.movementRepository = movementRepository;
		this.accountRepository = accountRepository;
	}
	
	@Override
	public void create(Movement movement) {
		validatePreCreate(movement);
		movement.addBalance(getAccountBalance(movement.getAccount().getId()));
		if (!movement.isBalanceValid()) {
			throw new ResourceBadRequestException("Saldo no disponible");
		}
		if (movement.getType() == EMovementType.Retiro &&
				!movement.canDebitToday(movementRepository.getTodayDebitTotal(movement.getAccount().getId()))) {
			throw new ResourceBadRequestException("Cupo diario Excedido");
		}
		movementRepository.save(movement);
	}
	
	private Double getAccountBalance(Long accountId) {
		Movement lastMovement = movementRepository.findLastMovementByAccountId(accountId).orElse(null);
		if (lastMovement != null) {
			return lastMovement.getBalance();
		}
		Account account = accountRepository.findById(accountId).orElse(null);
		if (account != null) {
			return account.getInitBalance();
		}
		throw new ResourceNotFoundException("AccountId "+accountId+" not found", "ACCOUNT_NOT_FOUND");
	}

	

	@Override
	public Movement findById(Long movementId) {
		return movementRepository.findById(movementId).orElseThrow(() -> new ResourceNotFoundException("MovementId "+movementId+" not found", "MOVEMENT_NOT_FOUND"));
	}
	
	@Override
	public List<Movement> findAll() {
		return movementRepository.findAll();
	}

	@Override
	public void delete(Long movementId) {
		final Movement movement = findById(movementId);		
		movementRepository.delete(movement);
	}
	
	private void validatePreCreate(Movement movement) {
		if(!movement.isValid()) {
			throw new ResourceBadRequestException("INVALID_REQUEST");
		}
		if(!accountRepository.existsById(movement.getAccount().getId())) {
			throw new ResourceNotFoundException("AccountId "+movement.getAccount().getId()+" not found", "ACCOUNT_NOT_FOUND");
		}
	}

}
