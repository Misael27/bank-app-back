package com.bankappback.model;

import java.util.Date;
import java.util.Objects;

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
@SuppressWarnings("unused")
public class Movement implements ICommonDomain {

	private Long id;
	
    private Date date;

	private EMovementType type;
    
    private Double value;
    
    private Double balance;
    
    private Account account;
    
    public Movement(EMovementType type, Double value, Double balance, Account account) {
    	this.type = type;
    	this.value = value;
    	this.balance = balance;
    	this.account = account;
    	this.date = new Date();
    }
    
    @Override
    public boolean isValid() {
    	if (Objects.isNull(type)) {
    		return false;
    	}
    	return true;
    }
    
}