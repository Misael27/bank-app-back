package com.bankappback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ProductDetail
 * 
 * @author mjpol
 *
 */
public class ProductDetail {

    @JsonProperty(required = true)
	private String id;
	
    @JsonProperty(required = true)
    private String name;
	
    @JsonProperty(required = true)
	private Double price;
	
    @JsonProperty(required = true)
	private Boolean availability;
	
	public ProductDetail() {
	}
	
	public ProductDetail(String id, String name, Double price, Boolean availability) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.availability = availability;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}
	
}
