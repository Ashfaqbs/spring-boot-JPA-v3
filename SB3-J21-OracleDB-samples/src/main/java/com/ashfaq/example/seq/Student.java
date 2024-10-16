package com.ashfaq.example.seq;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "xstudents_table")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq_gen")
	@SequenceGenerator(name = "student_seq_gen", sequenceName = "student_id_seq", allocationSize = 1)
	private Long id;
	private String name;
	private Integer age;
	private String address;

}
//this is a dedicated sequence created for this table as we had altered the column 
//but (not so dedicated way )we can create a sequence and not modify the table column and just define in 
//sequence generator on the id to use , but careful on the limit or allocation size and the next value as it 
// should be greater than max value in the table