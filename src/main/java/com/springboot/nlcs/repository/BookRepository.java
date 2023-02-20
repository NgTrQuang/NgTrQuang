package com.springboot.nlcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.nlcs.models.Book;

import java.util.List;
//import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);
    List<Book> findAll();
//    List<Book> findByPriceContaining(String price);
}
