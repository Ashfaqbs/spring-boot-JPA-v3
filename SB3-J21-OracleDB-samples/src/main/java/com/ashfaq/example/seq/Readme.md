
## Already a Table exists


### Flow :
(dedicated sequence)
- create table 
- insert data
- define seq 
- Alter table column id (so it will be a dedicated sequence table)


Sql's

```

CREATE TABLE xstudents_table (
    id NUMBER NOT NULL,
    name VARCHAR2(100),
    age NUMBER,
    address VARCHAR2(255),
    PRIMARY KEY (id)
);


INSERT INTO xstudents_table (id, name, age, address) 
VALUES
(1, 'John Doe', 25, '123 Main St');,
(2, 'Jane Smith', 22, '456 Oak Ave');
(3, 'Mike Johnson', 27, '789 Pine Rd');
(4, 'Anna Lee', 24, '101 Maple St');

COMMIT;

--Verify	
SELECT  * FROM SPRINGBOOTDEV.xstudents_table;

--Oracle sequence creation
CREATE SEQUENCE student_id_seq START WITH 5 INCREMENT BY 1;
 
--Alter table
ALTER TABLE SPRINGBOOTDEV.xstudents_table MODIFY id DEFAULT student_id_seq.NEXTVAL;
COMMIT;

--Verify 
-- we are not providing id so it will be auto generated
INSERT INTO SPRINGBOOTDEV.xstudents_table (name, age, address) 
VALUES ('Alice Brown', 30, '99 Elm St');

-OR 

INSERT INTO SPRINGBOOTDEV.XSTUDENTS_TABLE (id, name, age, address) 
VALUES(SPRINGBOOTDEV.student_id_seq.NEXTVAL,'John wick', 25, '123 Main St');

COMMIT;
```


## Code :
```
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




package com.ashfaq.example.seq;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {

}




```


#### Verify
```
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


```


### Note :

- This is a dedicated sequence created for this table as we had altered the column 
 but (not so dedicated way )we can create a sequence and not modify the table column and just  define in 
 sequence generator on the id to use , but careful on the limit or allocation size and the next  value as it 
 should be greater than max value in the table

- We Can also use the sequence in the variables where auto increment is needed and not only on id
