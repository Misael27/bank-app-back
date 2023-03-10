package com.bankappback.controller;

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
import org.springframework.web.bind.annotation.PutMapping;

import com.bankappback.exception.ErrorDetails;
import com.bankappback.model.Client;
import com.bankappback.service.IClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * 
 * @author mjpol
 *
 */
@RestController
@RequestMapping("/client")
public class ClientController {
	
	private IClientService clientService;
	
	public ClientController(IClientService clientService) {
		this.clientService = clientService;
	}

	/**
	 * PostClient create
	 * 
	 * @param productId
	 * @return
	 */
	@Operation(summary = "Create client", tags = { "Client" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = Client.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Client> postCreate(final @Parameter(description = "Client create", required = true) @RequestBody @Valid Client client) {
		clientService.create(client);
		return ResponseEntity.status(HttpStatus.CREATED).body(client);
	}
	
	/**
	 * GgetClientById
	 * 
	 * @param productId
	 * @return
	 */
	@Operation(summary = "Get client by id", tags = { "Client" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Client.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@GetMapping(value = "/{clientId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Client> getClientById(
			final @Parameter(description = "Client ID", in = ParameterIn.PATH, required = true) @PathVariable("clientId") Long clientId) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(clientId));
	}
	
	
	/**
	 * PutClient update
	 * 
	 * @param productId
	 * @return
	 */
	@Operation(summary = "Update client", tags = { "Client" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Client.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@PutMapping(value = "/{clientId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Client> putUpdate(
			final @Parameter(description = "Client ID", in = ParameterIn.PATH, required = true) @PathVariable("clientId") Long clientId,
			final @Parameter(description = "Client update", required = true) @RequestBody @Valid Client client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.update(clientId, client));
	}
	
	/**
	 * deleteClientById
	 * 
	 * @param productId
	 * @return
	 */
	@Operation(summary = "Delete client by id", tags = { "Client" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Client.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@DeleteMapping(value = "/{clientId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteClientById(
			final @Parameter(description = "Client ID", in = ParameterIn.PATH, required = true) @PathVariable("clientId") Long clientId) {
		clientService.delete(clientId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}

