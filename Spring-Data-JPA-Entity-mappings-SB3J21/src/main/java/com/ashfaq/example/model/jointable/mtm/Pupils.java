package com.ashfaq.example.model.jointable.mtm;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pupils_table")
@AllArgsConstructor
@NoArgsConstructor                                             
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
public class Pupils {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToMany()
//	 @JoinTable(
//		        name = "pupil_course_table",//so now the new table will be created with name = "pupil_course_table" which will have 
//		        //the columns "p_id" and "c_id" i.e in small man
//		        
//		        
//		        //renaming column names
//		        joinColumns = @JoinColumn(name = "p_ID"),
//		        inverseJoinColumns = @JoinColumn(name = "c_ID")
//		    )
	private Set<Course> courses;

}
