package com.bankappback.response;

import com.bankappback.model.EAccountType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountResponse {
	
	private Long id;
	private String number;
 	private EAccountType type;   
    private Double initBalance;
    private Boolean state;
    private Long clientId;
    
}
