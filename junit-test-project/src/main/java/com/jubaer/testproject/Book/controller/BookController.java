package com.jubaer.testproject.Book.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jubaer.testproject.Book.model.Book;
import com.jubaer.testproject.Book.repository.BookRepository;

@RestController
@RequestMapping(value="/book")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping
	public List<Book> getAllBookRecords(){
		return bookRepository.findAll();
	}
	
	@GetMapping(value="{id}")
	public Book getBookById(@PathVariable(value="id") Long id) {
		return bookRepository.findById(id).get();
	}
	
	@PostMapping
	public Book createBookRecord(@RequestBody Book bookRecord) {
		return bookRepository.save(bookRecord);
	}
	
	@PutMapping
	public Book updateBookRecord(@RequestBody Book bookRecord){
		if(bookRecord == null || bookRecord.getId() == null) {
//			 return "Book Record or Id must not be null";
		}
		
		Optional<Book> optionalBook = bookRepository.findById(bookRecord.getId());
		if(!optionalBook.isPresent()) {
//			return "Book with ID: "+bookRecord.getId()+" does not exist";
		}
		
		Book existingBookRecord = optionalBook.get();
		existingBookRecord.setName(bookRecord.getName());
		existingBookRecord.setSummary(bookRecord.getSummary());
		existingBookRecord.setRating(bookRecord.getRating());
		
		return bookRepository.save(existingBookRecord);
		
	}
	
	
	@DeleteMapping(value = "{id}")
	public void deleteBookById(@PathVariable(value="id") Long bookId) throws NotFoundException{
		if(!bookRepository.findById(bookId).isPresent()) {
			throw new NotFoundException();
		}
		bookRepository.deleteById(bookId);
	}
	
	
	
	
	
	

}
