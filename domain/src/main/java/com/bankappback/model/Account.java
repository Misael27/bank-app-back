package com.bankappback.model;

import java.util.Date;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;

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
public class Account implements ICommonDomain {

	private Long id;
	
	private String number;
    
	private EAccountType type;
    
    private Double initBalance;
    
    private Boolean state;

    private Client client;
    
    public Account(String number, EAccountType type, Double initBalance, Boolean state, Client client) {
    	this.number = number;
    	this.type = type;
    	this.initBalance = initBalance;
    	this.state = state;
    	this.client = client;
    }
    
    @Override    
    public boolean isValid() {
    	if (Strings.isEmpty(number)) {
    		return false;
    	}
    	return true;
    }
    
}
