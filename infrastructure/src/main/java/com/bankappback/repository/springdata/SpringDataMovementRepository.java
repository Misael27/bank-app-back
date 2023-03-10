package com.bankappback.repository.springdata;

import org.springframework.data.repository.CrudRepository;

import com.bankappback.mapping.MovementTable;

public interface SpringDataMovementRepository extends CrudRepository<MovementTable, Long> {

	
}