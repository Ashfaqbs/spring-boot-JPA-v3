package com.ashfaq.example.criteria.example;

import java.util.List;

import lombok.Data;

@Data
public class GymMemberSearchRequest {

	private String name;
	private Double weight;
	private List<String> addressList;
	private List<String> locationList;
	private List<Double> heightList;

}
