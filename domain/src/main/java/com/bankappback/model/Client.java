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
public class Client extends Person implements ICommonDomain {
	
	private Long id;
	
	private String password;
    
    private Boolean state;
    
    public Client(String name, EGenger gender, Date birthdate, String personId, 
    		String address, String phone, String password, Boolean state) {
    	this.name = name;
    	this.gender = gender;
    	this.birthdate = birthdate;
    	this.personId = personId;
    	this.address = address;
    	this.phone = phone;
    	this.password = password;
    	this.state = state;
    }
    
    @Override
    public boolean isValid() {
    	if (Strings.isEmpty(name)) {
    		return false;
    	}
    	return true;
    }
    
}
