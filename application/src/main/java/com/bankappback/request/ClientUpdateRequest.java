package com.bankappback.request;

import java.util.Date;

import com.bankappback.model.EGenger;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientUpdateRequest {
	
	@NotBlank(message = "NAME_NOT_BLANK")
	@Size(max = 100, message = "NAME_SIZE_MAX_50")
	protected String name;
	
	protected EGenger gender;
    
	@JsonFormat(pattern="yyyy-MM-dd")
	protected Date birthdate;
    
	protected String personId;
	
	protected String address;
	
	protected String phone;
		
	private String password;
    
    private Boolean state;
    
}
