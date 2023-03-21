package com.bankappback.service.impl;

import java.util.Date;
import java.util.List;

import com.bankappback.model.MovementReport;
import com.bankappback.repository.IReportRepository;
import com.bankappback.service.IReportService;

public class ReportService implements IReportService {
	
	IReportRepository reportService;
	
	public ReportService(IReportRepository reportService) {
		this.reportService = reportService;
	}

	@Override
	public List<MovementReport> getMovementReport(Long clientId, Date startDate, Date endDate) {
		return reportService.getMovementReport(clientId, startDate, endDate);
	}

}
