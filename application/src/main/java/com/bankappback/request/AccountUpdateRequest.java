package com.bankappback.request;

import com.bankappback.model.Client;
import com.bankappback.model.EAccountType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountUpdateRequest {
	
	@NotBlank(message = "NUMBER_NOT_BLANK")
	private String number;
    
	private EAccountType type;

	@Min(value = 0L, message = "INIT_BALANCE_MUST_BE_POSITIVE")
    private Double initBalance;
    
    private Boolean state;

    private Long clientId;
    
}
