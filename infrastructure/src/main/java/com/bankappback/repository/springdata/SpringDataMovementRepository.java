package com.bankappback.repository.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bankappback.mapping.MovementTable;

public interface SpringDataMovementRepository extends CrudRepository<MovementTable, Long> {

	Optional<MovementTable> findFirstByAccountIdOrderByDateDesc(Long accountId);

	@Query("SELECT sum(m.balance) FROM MovementTable m WHERE m.account.id = ?1 AND year(m.date) = ?2 AND month(m.date) = ?3 AND day(m.date) = ?4 AND type = 'Retiro'")
	Optional<Double> sumDebitByAccountAndDate(Long accountId, int year, int month, int day);
	
}