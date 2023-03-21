package com.bankappback.response;

import java.util.Date;

import com.bankappback.model.EGenger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientResponse {

	private Long id;	
	protected String name;
	protected EGenger gender;
	protected Date birthdate;
	protected String personId;
	protected String address;
	protected String phone;
    private Boolean state;
	
}
