package com.bankappback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bankappback.exception.ErrorDetails;
import com.bankappback.mapper.Mapper;
import com.bankappback.model.Movement;
import com.bankappback.request.MovementCreateRequest;
import com.bankappback.response.MovementResponse;
import com.bankappback.service.IMovementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import jakarta.validation.Valid;

/**
 * 
 * @author mjpol
 *
 */
@RestController
@RequestMapping("/movement")
public class MovementController {
	
	private IMovementService movementService;
	private Mapper<Movement,MovementCreateRequest,MovementCreateRequest,MovementResponse> mapper;
	
	public MovementController(IMovementService movementService) {
		this.movementService = movementService;
		this.mapper = new Mapper<Movement, MovementCreateRequest, MovementCreateRequest, MovementResponse>(Movement.class, MovementCreateRequest.class, MovementCreateRequest.class, MovementResponse.class);
	}

	/**
	 * PostMovement create
	 * 
	 * @param movementId
	 * @return
	 */
	@Operation(summary = "Create movement", tags = { "Movement" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = MovementResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MovementResponse> postCreate(final @Parameter(description = "Movement create", required = true) @RequestBody @Valid MovementCreateRequest request) {
		Movement movement = mapper.mapDtoToEntity(request);
		movementService.create(movement);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapEntityToResponse(movement));
	}
	
	/**
	 * GgetMovementById
	 * 
	 * @param movementId
	 * @return
	 */
	@Operation(summary = "Get movement by id", tags = { "Movement" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MovementResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@GetMapping(value = "/{movementId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MovementResponse> getMovementById(
			final @Parameter(description = "Movement ID", in = ParameterIn.PATH, required = true) @PathVariable("movementId") Long movementId) {
		return ResponseEntity.status(HttpStatus.OK).body(mapper.mapEntityToResponse(movementService.findById(movementId)));
	}
	
    /**
     * GetAllMovement
     * 
     * @return
     */
    @Operation(summary = "Movement list", tags = { "Movement" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = 
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = MovementResponse.class)))),
    })
    @GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<MovementResponse>> GetAllMovement() {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapAllEntityToResponse(movementService.findAll()));
    }
	
	/**
	 * deleteMovementById
	 * 
	 * @param movementId
	 * @return
	 */
	@Operation(summary = "Delete movement by id", tags = { "Movement" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@DeleteMapping(value = "/{movementId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteMovementById(
			final @Parameter(description = "Movement ID", in = ParameterIn.PATH, required = true) @PathVariable("movementId") Long movementId) {
		movementService.delete(movementId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}

