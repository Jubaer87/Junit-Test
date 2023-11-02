package com.jubaer.testproject.Book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="book_record")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String summary;
	private int rating;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Book() {
		
		// TODO Auto-generated constructor stub
	}
	public Book(Long id, @NonNull String name, @NonNull String summary, int rating) {
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.rating = rating;
	}
	
	
	
	

}
