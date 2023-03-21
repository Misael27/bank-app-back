package com.bankappback.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovementReport {
	
	private Integer id;
	private Date date;
	private String name;
	private String number;
	private EAccountType type;
    private Boolean state;
    private Double initbalance;
    private Double movement;
    private Double balance;
   
}
