package com.bankappback.model;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

	@NotNull(message = "NAME_NOT_NULL")
	@NotBlank(message = "NAME_NOT_BLANK")
	@Size(max = 100, message = "NAME_SIZE_MAX_50")
	protected String name;
	
	@NotNull(message = "GENDER_NOT_NULL")
	protected EGenger gender;
    
	@NotNull(message = "BIRTHDATE_NOT_NULL")
	protected Date birthdate;
    
	@NotNull(message = "PERSONID_NOT_NULL")
	protected String personId;
	
	@NotNull(message = "ADDRESS_NOT_NULL")
	protected String address;
	
	@NotNull(message = "PHONE_NOT_NULL")
	protected String phone;
    
}
