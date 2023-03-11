package com.bankappback.repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.bankappback.mapping.MovementTable;
import com.bankappback.model.Movement;
import com.bankappback.repository.springdata.SpringDataMovementRepository;

@Repository
public class MovementRepository implements IMovementRepository {

	SpringDataMovementRepository springDataMovementRepository;
	ModelMapper mapper;
	
	public MovementRepository(SpringDataMovementRepository springDataMovementRepository) {
		this.springDataMovementRepository = springDataMovementRepository;
		this.mapper = new ModelMapper();
		this.mapper.getConfiguration().setSkipNullEnabled(true);
	}

	@Override
	public void save(Movement movement) {
		MovementTable movementTable = this.mapper.map(movement, MovementTable.class);
		springDataMovementRepository.save(movementTable);
		movement.setId(movementTable.getId());
	}

	@Override
	public Optional<Movement> findById(Long id) {
		MovementTable result = springDataMovementRepository.findById(id).orElse(null);
		Movement movement = result != null ? mapper.map(result, Movement.class) : null;
		return Optional.ofNullable(movement);
	}
	
	@Override
	public List<Movement> findAll() {
		var result = new ArrayList<Movement>();
		springDataMovementRepository.findAll().forEach(movmentTable -> {
			result.add(mapper.map(movmentTable, Movement.class));
		});
		return result;
	}
	
	@Override
	public void delete(Movement movement) {
		springDataMovementRepository.deleteById(movement.getId());
	}
	
	@Override
	public Optional<Movement> findLastMovementByAccountId(Long accountId) {
		MovementTable result = springDataMovementRepository.findFirstByAccountIdOrderByDateDesc(accountId).orElse(null);
		Movement movement = result != null ? mapper.map(result, Movement.class) : null;
		return Optional.ofNullable(movement);
	}
	
	@Override
	public Double getTodayDebitTotal(Long accountId) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return springDataMovementRepository.sumDebitByAccountAndDate(accountId, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()).orElse(0.0d);
	}
	
}
