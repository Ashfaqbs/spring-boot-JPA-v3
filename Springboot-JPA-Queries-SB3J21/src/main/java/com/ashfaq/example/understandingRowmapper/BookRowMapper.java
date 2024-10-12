package com.ashfaq.example.understandingRowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookRowMapper implements RowMapper<Book> {
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor_id(rs.getInt("author_id"));
		return book;
	}
}


//Rowmapper'
//
//A RowMapper is an interface in Spring's JDBC framework used to map rows of a result set from a database query to a Java object. It simplifies the process of transforming database records into meaningful Java entities without having to manually extract the data from each ResultSet.
//
//What is a RowMapper?
//When you execute a query, the result comes back as a ResultSet, which is basically a collection of rows from the database. A RowMapper helps convert each row of this ResultSet into a Java object, like a DTO (Data Transfer Object) or an entity class. It's often used with Spring's JdbcTemplate to avoid repetitive code when converting from database to Java objects.
//
//Why use a RowMapper?
//Normally, with plain JDBC, you would write code to iterate through the ResultSet, extract each column manually, and populate the fields of your Java object. This can get repetitive. With RowMapper, you can centralize this mapping logic and make your code cleaner.
//
//How does it work?
//The RowMapper interface has a single method mapRow(ResultSet rs, int rowNum), where:
//
//ResultSet: Contains the data retrieved from the database.
//rowNum: The row number in the result set being processed.

