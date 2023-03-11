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
public class Movement implements ICommonDomain {

	private Long id;
    private Date date;
	private EMovementType type;
    private Double value;
    private Double balance;
    private Account account;
	final Double DAILY_TOP_VALUE = 1000d;
    
    public Movement(EMovementType type, Double value, Double balance, Account account) {
    	this.type = type;
    	this.value = value;
    	this.balance = balance;
    	this.account = account;
    	this.date = new Date();
    }
    
    @Override
    public boolean isValid() {
    	if (Objects.isNull(type) || Objects.isNull(value) || value < 0 || Objects.isNull(account)) {
    		return false;
    	}
    	return true;
    }

	public void addBalance(Double accountBalance) {
		balance = switch (type) {
    		case Retiro -> accountBalance - value;
    		case Deposito -> accountBalance + value;
    		default -> throw new IllegalStateException("Unexpected value: " + type);
		};
		date = new Date();
	}
	
	public Boolean isBalanceValid() {
		return balance >= 0;
	}

	public boolean canDebitToday(Double todayDebitTotal) {
		return value + todayDebitTotal <= 1000;
	}
    
}