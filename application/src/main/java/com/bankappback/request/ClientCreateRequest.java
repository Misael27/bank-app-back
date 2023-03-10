package com.bankappback.request;

import java.util.Date;

import com.bankappback.model.EGenger;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientCreateRequest {
	
	@NotNull(message = "NAME_NOT_NULL")
	@NotBlank(message = "NAME_NOT_BLANK")
	@Size(max = 100, message = "NAME_SIZE_MAX_50")
	protected String name;
	
	@NotNull(message = "GENDER_NOT_NULL")
	protected EGenger gender;
    
	@NotNull(message = "BIRTHDATE_NOT_NULL")
	@JsonFormat(pattern="yyyy-MM-dd")
	protected Date birthdate;
    
	@NotNull(message = "PERSONID_NOT_NULL")
	protected String personId;
	
	@NotNull(message = "ADDRESS_NOT_NULL")
	protected String address;
	
	@NotNull(message = "PHONE_NOT_NULL")
	protected String phone;

	@NotNull(message = "PASSWORD_NOT_NULL")
	private String password;
    
	@NotNull(message = "STATE_NOT_NULL")
    private Boolean state;
	
}
