package com.jubaer.testproject.Book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jubaer.testproject.Book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
