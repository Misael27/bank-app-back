package com.bankappback.repository;

import java.util.List;
import java.util.Optional;

import com.bankappback.model.Movement;

public interface IMovementRepository {

	void save(Movement movement);
	
	Optional<Movement> findById(Long id);

	void delete(Movement movement);

	List<Movement> findAll();

	Optional<Movement> findLastMovementByAccountId(Long accountId);

	Double getTodayDebitTotal(Long accountId);

}
