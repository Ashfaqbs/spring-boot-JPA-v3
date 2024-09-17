package com.ashfaq.example.criteria.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GymMembers_Table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GymMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String address;

	private String location;

	private Double weight;

	private Double height;

	private String supportGuy;
}
