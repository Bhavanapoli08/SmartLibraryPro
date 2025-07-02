package com.smartlibrarypro.springbootlibrary.controller;

import com.smartlibrarypro.springbootlibrary.entity.Book;
import com.smartlibrarypro.springbootlibrary.responsemodels.ShelfCurrentLoansResponse;
import com.smartlibrarypro.springbootlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")  // ✅ Optional: CORS support for frontend
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 🔓 Public endpoint
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Library Service";
    }

    // 🔐 Secure endpoints (require authentication)

    @GetMapping("/secure/currentloans")
    public List<ShelfCurrentLoansResponse> getCurrentLoans(Authentication authentication) throws Exception {
        String userEmail = authentication.getName();
        return bookService.currentLoans(userEmail);
    }

    @GetMapping("/secure/currentloans/count")
    public int getCurrentLoansCount(Authentication authentication) {
        String userEmail = authentication.getName();
        return bookService.currentLoansCount(userEmail);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean isBookCheckedOutByUser(Authentication authentication,
                                          @RequestParam Long bookId) {
        String userEmail = authentication.getName();
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook(Authentication authentication,
                              @RequestParam Long bookId) throws Exception {
        String userEmail = authentication.getName();
        return bookService.checkoutBook(userEmail, bookId);
    }

    @PutMapping("/secure/return")
    public void returnBook(Authentication authentication,
                           @RequestParam Long bookId) throws Exception {
        String userEmail = authentication.getName();
        bookService.returnBook(userEmail, bookId);
    }

    @PutMapping("/secure/renew/loan")
    public void renewLoan(Authentication authentication,
                          @RequestParam Long bookId) throws Exception {
        String userEmail = authentication.getName();
        bookService.renewLoan(userEmail, bookId);
    }
}
