package com.bankappback.service;

import java.util.Date;
import java.util.List;

import com.bankappback.model.MovementReport;

public interface IReportService {

	List<MovementReport> getMovementReport(Long clientId, Date startDate, Date endDate);

}
