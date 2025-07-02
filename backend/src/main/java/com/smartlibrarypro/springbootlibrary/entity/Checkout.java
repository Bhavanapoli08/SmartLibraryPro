package com.smartlibrarypro.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "checkout")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "checkout_date", nullable = false)
    private String checkoutDate;

    @Column(name = "return_date", nullable = false)
    private String returnDate;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    // ✅ Custom constructor for BookService (without ID)
    public Checkout(String userEmail, String checkoutDate, String returnDate, Long bookId) {
        this.userEmail = userEmail;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
    }
}

