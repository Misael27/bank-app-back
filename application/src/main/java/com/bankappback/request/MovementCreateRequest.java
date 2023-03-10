package com.bankappback.request;

import com.bankappback.model.EMovementType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author mjpol
 *
 */
@NoArgsConstructor
@Getter
@Setter
public class MovementCreateRequest {

	@NotNull(message = "TYPE_NOT_NULL")
	private EMovementType type;
	
	@NotNull(message = "VALUE_NOT_NULL")
	@Min(value = 0L, message = "VALUE_MUST_BE_POSITIVE")
    private Double value;
	
    @NotNull(message = "ACCOUNTID_NOT_NULL")
    private Long accountId;    
   
}
