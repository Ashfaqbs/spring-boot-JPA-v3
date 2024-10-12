package com.ashfaq.example;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.ashfaq.example.join.model.Author;
import com.ashfaq.example.join.model.AuthorRepository;
import com.ashfaq.example.join.model.BookRepository;
import com.ashfaq.example.repo.EmployeeRepository;

import jakarta.persistence.EntityManager;

@SpringBootApplication
@EnableJpaAuditing // Enabling auditing here
public class SpringbootJpaQueriesSb3J21Application {

	
	@Bean
	public CommandLineRunner run(EmployeeRepository employeeRepository, EntityManager entityManager) {
	    return args -> {
//	    	System.out.println("Employee Queries started");
//	        employeeRepository.updateDepartmentByName("John Doe", "IT");
//	        employeeRepository.deleteByName("Jane Doe"); 
//	    	System.out.println("Employee Queries ended");
//Commenting out
	       
	    };
	}
	
	
	
	  @Bean
	    public CommandLineRunner runjoinQrys(AuthorRepository authorRepository, BookRepository bookRepository) {
	        return args -> {
	        	
		    	System.out.println("Join Queries started");

	            // Find authors by book title
	            List<Author> authors = authorRepository.findAuthorsByBookTitle("Harry Potter and the Sorcerer's Stone");
	            authors.forEach(author -> System.out.println("Author: " + author.getName()));

//	            // Find books by author name
//	            List<Book> books = bookRepository.findBooksByAuthorName("George R.R. Martin");
//	            books.forEach(book -> System.out.println("Book: " + book.getTitle()));
	            
	            
//		    	System.out.println("Join Queries ended");
		    	
//		    	
//		    	Join Queries started
//		    	Hibernate: select a1_0.id,a1_0.name from author_sample a1_0 join book_sample b1_0 on a1_0.id=b1_0.author_id where b1_0.title=?
//		    	Author: J.K. Rowling
//		    	Hibernate: select b1_0.id,b1_0.author_id,b1_0.title from book_sample b1_0 join author_sample a1_0 on a1_0.id=b1_0.author_id where a1_0.name=?
//		    	Hibernate: select a1_0.id,a1_0.name from author_sample a1_0 where a1_0.id=?
//		    	Book: A Game of Thrones
//		    	Book: A Clash of Kings
//		    	Join Queries ended

	        };
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaQueriesSb3J21Application.class, args);
	}

}

/*
SELECT id, department, name
FROM springbootdev.employee_db;

-- Drop tables if they already exist
DROP TABLE IF EXISTS Employee;

-- Create Employee table
CREATE TABLE Employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL
);

-- Insert initial data into Employee table
INSERT INTO springbootdev.employee_db (name, department) VALUES ('John Doe', 'HR');
INSERT INTO springbootdev.employee_db (name, department) VALUES ('Jane Doe', 'Finance');
INSERT INTO springbootdev.employee_db (name, department) VALUES ('Alice Smith', 'IT');
INSERT INTO springbootdev.employee_db (name, department) VALUES ('Bob Johnson', 'Marketing');
commit;





SELECT id, title, author_id
FROM springbootdev.book_sample;


SELECT id, name
FROM springbootdev.author_sample;



INSERT INTO springbootdev.author_sample (name) VALUES ('J.K. Rowling');
INSERT INTO springbootdev.author_sample (name) VALUES ('George R.R. Martin');

INSERT INTO springbootdev.book_sample (title, author_id) VALUES ('Harry Potter and the Sorcerer\'s Stone', 1);
INSERT INTO springbootdev.book_sample (title, author_id) VALUES ('Harry Potter and the Chamber of Secrets', 1);
INSERT INTO springbootdev.book_sample (title, author_id) VALUES ('A Game of Thrones', 2);
INSERT INTO springbootdev.book_sample (title, author_id) VALUES ('A Clash of Kings', 2);

commit;


SELECT a.* 
FROM springbootdev.author_sample a 
JOIN springbootdev.book_sample b ON a.id = b.author_id 
WHERE b.title = 'Harry Potter and the Sorcerer''s Stone	';




*/


