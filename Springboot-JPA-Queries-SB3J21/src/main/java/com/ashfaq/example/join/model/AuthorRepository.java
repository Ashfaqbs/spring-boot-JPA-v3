package com.ashfaq.example.join.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	@Query("SELECT a FROM Author a JOIN a.books b WHERE b.title = :title")
    List<Author> findAuthorsByBookTitle(@Param("title") String title);

//    Note : this does not depend on BookRepository

//    SQL Query
//    SELECT a.* 
//    FROM springbootdev.author_sample a 
//    JOIN springbootdev.book_sample b ON a.id = b.author_id 
//    WHERE b.title = 'Harry Potter and the Sorcerer''s Stone	';

	// Working
	/*
	 * JPA Mapping:
	 * 
	 * When performing a join query using @Query in the AuthorRepository, the
	 * functionality doesn't directly depend on the BookRepository. Instead, it
	 * depends on how the relationship is defined between Author and Book entities
	 * in your JPA mappings.
	 * 
	 * JPA Mapping: The join operation is based on the relationship mapping between
	 * Author and Book. If you have defined the relationship correctly
	 * (e.g., @OneToMany in Author and @ManyToOne in Book), the join should work
	 * without involving the BookRepository.
	 * 
	 * @Query Annotation:
	 * 
	 * When you write a custom query using the @Query annotation, you're instructing
	 * JPA to fetch data based on the query you define. The query doesn't require
	 * any methods from BookRepository because it directly queries the underlying
	 * database.
	 * 
	 * 
	 * Conclusion: No Direct Dependency: The join operation in AuthorRepository
	 * doesn't depend on the BookRepository. The @Query annotation works
	 * independently as long as the entity relationships are correctly defined.
	 * 
	 */
}