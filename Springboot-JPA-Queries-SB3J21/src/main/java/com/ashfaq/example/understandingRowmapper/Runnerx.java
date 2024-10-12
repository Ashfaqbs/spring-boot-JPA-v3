package com.ashfaq.example.understandingRowmapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Runnerx implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Book> getAllbook() {
		String sql = "SELECT id, title, author_id FROM book_sample";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("row mapper sample : " + getAllbook());

//		op
//		row mapper sample : [Book(id=1, title=Harry Potter and the Sorcerer's Stone, author_id=1), Book(id=2, title=Harry Potter and the Chamber of Secrets, author_id=1), Book(id=3, title=A Game of Thrones, author_id=2), Book(id=4, title=A Clash of Kings, author_id=2)]
	}

}
