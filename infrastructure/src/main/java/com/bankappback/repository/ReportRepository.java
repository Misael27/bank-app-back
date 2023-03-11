package com.bankappback.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bankappback.model.MovementReport;
import com.bankappback.repository.springdata.SpringDataMovementRepository;

@Repository
public class ReportRepository implements IReportRepository  {

	SpringDataMovementRepository springDataMovementRepository;
	
	public ReportRepository(SpringDataMovementRepository springDataMovementRepository) {
		this.springDataMovementRepository = springDataMovementRepository;
	}

	@Override
	public List<MovementReport> getMovementReport(Long clientId, Date startDate, Date endDate) {
		return springDataMovementRepository.getMovementReport(clientId, startDate, endDate);
	}

}
