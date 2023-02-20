package com.springboot.nlcs.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.nlcs.repository.BookRepository;
import com.springboot.nlcs.exception.ResourceNotFoundException;
import com.springboot.nlcs.models.Book;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class BookController {
	
	BookRepository bookRepository;
	@Autowired
		public BookController(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

		//get all book
		@GetMapping("/books")
//		@PreAuthorize("hasRole('ROLE_ADMIN')") 
		public  ResponseEntity<?> getAllBooks() {
			List<Book> books = bookRepository.findAll();
			System.out.println("======TEST book=====> book = " + books);
			return ResponseEntity.ok(books);
		}
		
		//create book rest api
		@PostMapping("/books")
//		@PreAuthorize("hasRole('ROLE_ADMIN')") 
		public Book createBook(@RequestBody Book book) {
			return bookRepository.save(book);
		}
		
		//get book by id rest api
		@GetMapping("/books/{id}")
//		@PreAuthorize("hasRole('ROLE_ADMIN')") 
		public ResponseEntity<Book> getBookById(@PathVariable Long id) {
			Book book = bookRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Book not exist with id: " + id));
			return ResponseEntity.ok(book);
		}
		
		//update book rest api
		@PutMapping("/books/{id}")
//		@PreAuthorize("hasRole('ROLE_ADMIN')") 
		public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
			Book book = bookRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Book not exist with id: " + id));
			book.setName(bookDetails.getName());
			book.setPhoto(bookDetails.getPhoto());
			book.setPrice(bookDetails.getPrice());
			
			Book updatedBook = bookRepository.save(book);
			return ResponseEntity.ok(updatedBook);
		}
		
		@DeleteMapping("/books/{id}")
//		@PreAuthorize("hasRole('ROLE_ADMIN')") 
		public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long id){
			Book book = bookRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Book not exist with id: " + id));
			bookRepository.delete(book);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
}
