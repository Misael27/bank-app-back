package com.bankappback.request;

import com.bankappback.model.EAccountType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountCreateRequest {
	
	@NotNull(message = "NUMBER_NOT_NULL")
	@NotBlank(message = "NUMBER_NOT_BLANK")
	private String number;
	
	@NotNull(message = "TYPE_NOT_NULL")
	private EAccountType type;
	
	@NotNull(message = "INIT_BALANCE_NOT_NULL")
	@Min(value = 0L, message = "INIT_BALANCE_MUST_BE_POSITIVE")
    private Double initBalance;
	
	@NotNull(message = "STATE_NOT_NULL")
    private Boolean state;
	
	@NotNull(message = "CLIENTID_NOT_NULL")
    private Long clientId;
    
}
