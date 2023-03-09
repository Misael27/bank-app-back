package com.bankappback.model;


import java.util.Date;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.MessageDigest;  

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
	
	@NotNull(message = "PASSWORD_NOT_NULL")
	private String password;
    
	@NotNull(message = "STATE_NOT_NULL")
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
    @JsonIgnore
    public boolean isValid() {
    	if (Strings.isEmpty(name) || Objects.isNull(gender) || Objects.isNull(birthdate) || birthdate.after(new Date())
    			|| Strings.isEmpty(personId) || Strings.isEmpty(address) || Strings.isEmpty(phone) || Strings.isEmpty(password)
    			|| Objects.isNull(state)) {
    		return false;
    	}
    	return true;
    }
    
    @JsonIgnore
    public void encryptPassword() {
    	 password = getEncryptPass(password);
    }
    
    @JsonIgnore
    private String getEncryptPass(String plainPassword) {
    	int strength = 10; // work factor of bcrypt
	   	BCryptPasswordEncoder bCryptPasswordEncoder =
	   	new BCryptPasswordEncoder(strength, new SecureRandom());
	   	return bCryptPasswordEncoder.encode(plainPassword);
    }

    @JsonIgnore
	public void update(Client clientUpdate) {
		name = !Objects.isNull(clientUpdate.getName()) ? clientUpdate.getName() : name;
		gender = !Objects.isNull(clientUpdate.getGender()) ? clientUpdate.getGender() : gender;
		birthdate = !Objects.isNull(clientUpdate.getBirthdate()) ? clientUpdate.getBirthdate() : birthdate;
		personId = !Objects.isNull(clientUpdate.getPersonId()) ? clientUpdate.getPersonId() : personId;
		address = !Objects.isNull(clientUpdate.getAddress()) ? clientUpdate.getAddress() : address;
		phone = !Objects.isNull(clientUpdate.getPhone()) ? clientUpdate.getPhone() : phone;
		password = !Objects.isNull(clientUpdate.getPassword()) ? getEncryptPass(clientUpdate.getPassword()) : password;
	}
    
}
