package com.ashfaq.example.secondary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Restaurant.findByName", query = "SELECT r FROM Restaurant r WHERE r.name = :name")
@NamedQuery(name = "Restaurant.findByAddressAndName", query = "SELECT r FROM Restaurant r WHERE r.address = :address AND r.name = :name")
public class Restaurant {
	
	@Id
	private Long id;
	private String name;
	private String address;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}