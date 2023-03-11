package com.bankappback.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.bankappback.model.MovementReport;
import com.bankappback.service.IReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.ArraySchema;

/**
 * 
 * @author mjpol
 *
 */
@RestController
@RequestMapping("/report")
public class ReportController {
	
	private IReportService reportService;
	
	public ReportController(IReportService reportService) {
		this.reportService = reportService;
	}

    /**
     * GetAllReport
     * 
     * @return
     */
    @Operation(summary = "Movement report", tags = { "Report" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = 
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = MovementReport.class)))),
    })
    @GetMapping(value = "/reports", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<MovementReport>> GetMovementReport(
    		@RequestParam("clientId") Long clientId,
    		@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
    		@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getMovementReport(clientId, startDate, endDate));
    }
	
}

