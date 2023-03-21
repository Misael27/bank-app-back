package com.bankappback.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author mjpol
 *
 */
@Getter
@Setter
public class Person {

	protected String name;
	protected EGenger gender;
	protected Date birthdate;
	protected String personId;
	protected String address;
	protected String phone;
    
}
