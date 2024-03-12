package com.MiniLibrary.MiniLibrary.Service;

import com.MiniLibrary.MiniLibrary.Entity.Book;
import com.MiniLibrary.MiniLibrary.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }


    public List<Book> searchBooks(String author, Double maxPrice, String title) {
        return bookRepository.findByAuthorContainingAndPriceLessThanAndTitleContaining(
                author, maxPrice, title);
    }
    }


