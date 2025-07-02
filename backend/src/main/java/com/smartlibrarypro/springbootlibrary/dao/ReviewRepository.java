package com.smartlibrarypro.springbootlibrary.dao;

import com.smartlibrarypro.springbootlibrary.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 📘 Retrieve all reviews by book ID with pagination
    Page<Review> findByBookId(Long bookId, Pageable pageable);

    // 📘 Check if a user has reviewed a specific book
    Review findByUserEmailAndBookId(String userEmail, Long bookId);

    // 🧹 Delete all reviews for a specific book
    @Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.bookId = :bookId")
    void deleteAllByBookId(@Param("bookId") Long bookId);
}
