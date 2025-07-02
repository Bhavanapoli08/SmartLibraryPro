package com.smartlibrarypro.springbootlibrary.dao;

import com.smartlibrarypro.springbootlibrary.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    // ✅ Find a checkout record for a specific user and book
    Checkout findByUserEmailAndBookId(String userEmail, Long bookId);

    // ✅ Find all checkouts by user email
    List<Checkout> findByUserEmail(String userEmail);

    // ✅ Delete all checkouts for a specific book ID
    @Transactional
    @Modifying
    @Query("DELETE FROM Checkout c WHERE c.bookId = :bookId")
    void deleteAllByBookId(@Param("bookId") Long bookId);
}

