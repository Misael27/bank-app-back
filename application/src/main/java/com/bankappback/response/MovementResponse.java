package com.bankappback.response;

import java.util.Date;

import com.bankappback.model.EMovementType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MovementResponse {

	private Long id;
    private Date date;
	private EMovementType type;
    private Double value;
    private Double balance;
    private Long accountId;    
	
}
