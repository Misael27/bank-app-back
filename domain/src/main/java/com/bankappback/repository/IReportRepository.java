package com.bankappback.repository;

import java.util.Date;
import java.util.List;

import com.bankappback.model.MovementReport;

public interface IReportRepository {

	List<MovementReport> getMovementReport(Long clientId, Date startDate, Date endDate);

}
