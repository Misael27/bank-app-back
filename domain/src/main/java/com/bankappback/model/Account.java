package com.bankappback.model;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    	if (Strings.isEmpty(number) || Objects.isNull(type) || Objects.isNull(initBalance)
    			|| initBalance < 0 || Objects.isNull(state) || Objects.isNull(client) || Objects.isNull(client.getId())) {
    		return false;
    	}
    	return true;
    }

	@JsonIgnore
	public void update(Account accountUpdate) {
		number = !Objects.isNull(accountUpdate.getNumber()) ? accountUpdate.getNumber() : number;
		type = !Objects.isNull(accountUpdate.getType()) ? accountUpdate.getType() : type;
		initBalance = !Objects.isNull(accountUpdate.getInitBalance()) ? accountUpdate.getInitBalance() : initBalance;
		state = !Objects.isNull(accountUpdate.getState()) ? accountUpdate.getState() : state;
		client = !Objects.isNull(accountUpdate.getClient()) ? accountUpdate.getClient() : client;	
	}
    
}
