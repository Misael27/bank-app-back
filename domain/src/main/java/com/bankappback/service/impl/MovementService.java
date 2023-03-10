package com.bankappback.service.impl;

import java.util.List;

import com.bankappback.exception.ResourceBadRequestException;
import com.bankappback.exception.ResourceNotFoundException;
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
		movement.setId(null);
		movementRepository.save(movement);
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
	}
	
	private void validatePreUpdate(Movement movement, Movement movementUpdate) {
		movement.update(movementUpdate);
	}

}
