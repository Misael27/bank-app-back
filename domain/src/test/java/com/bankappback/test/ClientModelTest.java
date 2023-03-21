package com.bankappback.test;


import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bankappback.model.Client;
import com.bankappback.model.EGenger;




public class ClientModelTest {
	
	@Test
	void IsValid_WhenClientDataIsNotCompleteReturnFalse()
    {	
		//Arrange
		Client client = new Client();
		
		//Act
		var result = client.isValid();
		
		//Asserts
		Assertions.assertEquals(false, result);
		
    }
	
	@Test
	void IsValid_WhenClientDataIsCompleteReturnTrue()
    {	
		//Arrange
		Client client = new Client();
		client.setAddress("Test");
		client.setBirthdate(new Date());
		client.setGender(EGenger.M);
		client.setName("test");
		client.setPassword("1234");
		client.setPersonId("123644");
		client.setPhone("1565");
		client.setState(true);
		//Act
		var result = client.isValid();
		
		//Asserts
		Assertions.assertEquals(true, result);
		
    }

}
