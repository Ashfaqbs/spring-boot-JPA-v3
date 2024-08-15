package com.ashfaq.example.join.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository 
extends JpaRepository<Book, Long>   
{
//    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.name = :name")
//    List<Book> findBooksByAuthorName(@Param("name") String name);
}