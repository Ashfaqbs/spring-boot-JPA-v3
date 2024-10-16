package com.ashfaq.example.seq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	StudentRepo studentRepo;

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Verify the data in database");
//		// Verify the data in database
//		studentRepo.findAll().forEach(System.out::println);
		
//	op	
//		Verify the data in database
//		Hibernate: select s1_0.id,s1_0.address,s1_0.age,s1_0.name from xstudents_table s1_0
//		Student(id=1, name=John Doe, age=25, address=123 Main St)
//		Student(id=2, name=Jane Smith, age=22, address=456 Oak Ave)
//		Student(id=3, name=Mike Johnson, age=27, address=789 Pine Rd)
//		Student(id=4, name=Anna Lee, age=24, address=101 Maple St)
//		Student(id=6, name=John wick, age=25, address=123 Main St)
//		Student(id=5, name=John wick, age=25, address=123 Main St)
//		Student(id=7, name=John wick, age=25, address=123 Main St)
//		Student(id=8, name=Alice Brown, age=30, address=99 Elm St)
		
		
//		System.out.println("Verify the data in database");

//		Student student = new Student();
//		student.setName("Ashfaq");
//		student.setAge(25);
//		student.setAddress("India");
//		studentRepo.save(student);
//		studentRepo.findAll().forEach(System.out::println);
		

	//OP logs
//		Verify the data in database
//		Hibernate: select student_id_seq.nextval from dual
//		Hibernate: insert into xstudents_table (address,age,name,id) values (?,?,?,?)
//		Hibernate: select s1_0.id,s1_0.address,s1_0.age,s1_0.name from xstudents_table s1_0
//		Student(id=25, name=Ashfaq, age=25, address=India)
//		Student(id=1, name=John Doe, age=25, address=123 Main St)
//		Student(id=2, name=Jane Smith, age=22, address=456 Oak Ave)
//		Student(id=3, name=Mike Johnson, age=27, address=789 Pine Rd)
//		Student(id=4, name=Anna Lee, age=24, address=101 Maple St)
//		Student(id=6, name=John wick, age=25, address=123 Main St)
//		Student(id=5, name=John wick, age=25, address=123 Main St)
//		Student(id=7, name=John wick, age=25, address=123 Main St)
//		Student(id=8, name=Alice Brown, age=30, address=99 Elm St)
	
	
		
		//inserted the same data again now the logs 
		
//		Student(id=25, name=Ashfaq, age=25, address=India)
//		Student(id=26, name=Ashfaq, age=25, address=India)
//		Student(id=1, name=John Doe, age=25, address=123 Main St)
//		Student(id=2, name=Jane Smith, age=22, address=456 Oak Ave)
//		Student(id=3, name=Mike Johnson, age=27, address=789 Pine Rd)
//		Student(id=4, name=Anna Lee, age=24, address=101 Maple St)
//		Student(id=6, name=John wick, age=25, address=123 Main St)
//		Student(id=5, name=John wick, age=25, address=123 Main St)
//		Student(id=7, name=John wick, age=25, address=123 Main St)
//		Student(id=8, name=Alice Brown, age=30, address=99 Elm St)
		
		
		
	}
}
