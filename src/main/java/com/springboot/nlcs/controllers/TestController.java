package com.springboot.nlcs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.nlcs.models.Book;
import com.springboot.nlcs.repository.BookRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController { 
	@Autowired
	BookRepository bookRepository;
  
	@GetMapping("/all")
	public ResponseEntity<?> allAccess() {
		List<Book> bookList = bookRepository.findAll();
		return ResponseEntity.ok(bookList);
	}
	
  @GetMapping("/user")
  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
  public String userAccess() {
    return "User page: Shopping.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('ROLE_MODERATOR')")	
  public String moderatorAccess() {
    return "Moderator board: CRUD.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')") 
  public String adminAccess() {
    return "Admin board: Manage users and CRUD.";
  }
}
