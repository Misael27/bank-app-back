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
import org.springframework.web.bind.annotation.PutMapping;

import com.bankappback.exception.ErrorDetails;
import com.bankappback.mapper.Mapper;
import com.bankappback.model.Client;
import com.bankappback.request.ClientCreateRequest;
import com.bankappback.request.ClientUpdateRequest;
import com.bankappback.response.ClientResponse;
import com.bankappback.service.IClientService;

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
@RequestMapping("/client")
public class ClientController {
	
	private IClientService clientService;
	private Mapper<Client,ClientCreateRequest,ClientUpdateRequest,ClientResponse> mapper;
	
	public ClientController(IClientService clientService) {
		this.clientService = clientService;
		this.mapper = new Mapper<Client, ClientCreateRequest, ClientUpdateRequest, ClientResponse>(Client.class, ClientCreateRequest.class, ClientUpdateRequest.class, ClientResponse.class);
	}

	/**
	 * PostClient create
	 * 
	 * @param clientId
	 * @return
	 */
	@Operation(summary = "Create client", tags = { "Client" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ClientResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientResponse> postCreate(final @Parameter(description = "Client create", required = true) @RequestBody @Valid ClientCreateRequest request) {
		Client client = mapper.mapDtoToEntity(request);
		client.setPersonId(request.getPerson());
		clientService.create(client);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapEntityToResponse(client));
	}
	
	/**
	 * GgetClientById
	 * 
	 * @param clientId
	 * @return
	 */
	@Operation(summary = "Get client by id", tags = { "Client" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ClientResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@GetMapping(value = "/{clientId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientResponse> getClientById(
			final @Parameter(description = "Client ID", in = ParameterIn.PATH, required = true) @PathVariable("clientId") Long clientId) {
		return ResponseEntity.status(HttpStatus.OK).body(mapper.mapEntityToResponse(clientService.findById(clientId)));
	}
	
    /**
     * GetAllClient
     * 
     * @return
     */
    @Operation(summary = "Client list", tags = { "Client" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = 
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ClientResponse.class)))),
    })
    @GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ClientResponse>> GetAllClient() {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapAllEntityToResponse(clientService.findAll()));
    }
	
	
	/**
	 * PutClient update
	 * 
	 * @param clientId
	 * @return
	 */
	@Operation(summary = "Update client", tags = { "Client" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ClientResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@PutMapping(value = "/{clientId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientResponse> putUpdate(
			final @Parameter(description = "Client ID", in = ParameterIn.PATH, required = true) @PathVariable("clientId") Long clientId,
			final @Parameter(description = "Client update", required = true) @RequestBody @Valid ClientUpdateRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(mapper.mapEntityToResponse(clientService.update(clientId, mapper.mapDtoUpdateToEntity(request))));
	}
	
	/**
	 * deleteClientById
	 * 
	 * @param clientId
	 * @return
	 */
	@Operation(summary = "Delete client by id", tags = { "Client" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true))),
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

