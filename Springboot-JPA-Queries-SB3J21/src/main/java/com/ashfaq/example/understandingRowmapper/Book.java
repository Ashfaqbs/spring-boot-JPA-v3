package com.ashfaq.example.understandingRowmapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	int id;
	String title;
	int author_id;
}
