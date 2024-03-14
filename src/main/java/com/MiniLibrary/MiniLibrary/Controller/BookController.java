package com.MiniLibrary.MiniLibrary.Controller;

import com.MiniLibrary.MiniLibrary.Entity.Book;
import com.MiniLibrary.MiniLibrary.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String findAll(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book-list";
    }


    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book1_details.html"; // Имя представления для отображения деталей книги
    }

    @PostMapping("/books/save")
    public String saveBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books"; // Redirects back to the book list page
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка удаления книги: " + e.getMessage());
        }
    }
    @GetMapping("/books/search")
    public String searchBooks(@RequestParam(required = false) String author,
                              @RequestParam(required = false) Double maxPrice,
                              @RequestParam(required = false) String title,
                              Model model) {
        List<Book> searchResults = bookService.searchBooks(author, maxPrice, title);
        model.addAttribute("books", searchResults);
        return "search-results";
    }

}

