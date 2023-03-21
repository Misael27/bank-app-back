package com.bankappback.service;

import java.util.List;

import com.bankappback.model.Movement;

public interface IMovementService {

	void create(Movement movement);

	Movement findById(Long movementId);

	List<Movement> findAll();

	void delete(Long movementId);
	
}
