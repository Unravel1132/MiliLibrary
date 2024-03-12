package com.MiniLibrary.MiniLibrary.Repository;

import com.MiniLibrary.MiniLibrary.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);


    List<Book> findByAuthorContainingAndPriceLessThanAndTitleContaining(String author, Double maxPrice, String title);
}