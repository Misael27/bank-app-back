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
import com.bankappback.model.Account;
import com.bankappback.request.AccountCreateRequest;
import com.bankappback.request.AccountUpdateRequest;
import com.bankappback.response.AccountResponse;
import com.bankappback.service.IAccountService;

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
@RequestMapping("/account")
public class AccountController {
	
	private IAccountService accountService;
	private Mapper<Account,AccountCreateRequest,AccountUpdateRequest,AccountResponse> mapper;
	
	public AccountController(IAccountService accountService) {
		this.accountService = accountService;
		this.mapper = new Mapper<Account, AccountCreateRequest, AccountUpdateRequest, AccountResponse>(Account.class, AccountCreateRequest.class, AccountUpdateRequest.class, AccountResponse.class);
	}

	/**
	 * PostAccount create
	 * 
	 * @return
	 */
	@Operation(summary = "Create account", tags = { "Account" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccountResponse> postCreate(final @Parameter(description = "Account create", required = true) @RequestBody @Valid AccountCreateRequest request) {
		Account account = mapper.mapDtoToEntity(request);
		accountService.create(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapEntityToResponse(account));
	}
	
	/**
	 * GgetAccountById
	 * 
	 * @param accountId
	 * @return
	 */
	@Operation(summary = "Get account by id", tags = { "Account" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@GetMapping(value = "/{accountId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccountResponse> getAccountById(
			final @Parameter(description = "Account ID", in = ParameterIn.PATH, required = true) @PathVariable("accountId") Long accountId) {
		return ResponseEntity.status(HttpStatus.OK).body(mapper.mapEntityToResponse(accountService.findById(accountId)));
	}
	
    /**
     * GetAllAccount
     * 
     * @return
     */
    @Operation(summary = "Account list", tags = { "Account" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = 
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AccountResponse.class)))),
    })
    @GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<AccountResponse>> GetAllAccount() {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapAllEntityToResponse(accountService.findAll()));
    }
	
	
	/**
	 * PutAccount update
	 * 
	 * @param accountId
	 * @return
	 */
	@Operation(summary = "Update account", tags = { "Account" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@PutMapping(value = "/{accountId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccountResponse> putUpdate(
			final @Parameter(description = "Account ID", in = ParameterIn.PATH, required = true) @PathVariable("accountId") Long accountId,
			final @Parameter(description = "Account update", required = true) @RequestBody @Valid AccountUpdateRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(mapper.mapEntityToResponse(accountService.update(accountId, mapper.mapDtoUpdateToEntity(request))));
	}
	
	/**
	 * deleteAccountById
	 * 
	 * @param accountId
	 * @return
	 */
	@Operation(summary = "Delete account by id", tags = { "Account" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@DeleteMapping(value = "/{accountId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteAccountById(
			final @Parameter(description = "Account ID", in = ParameterIn.PATH, required = true) @PathVariable("accountId") Long accountId) {
		accountService.delete(accountId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}

