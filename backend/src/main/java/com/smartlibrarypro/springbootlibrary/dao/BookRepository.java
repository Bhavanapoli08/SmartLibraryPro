package com.smartlibrarypro.springbootlibrary.dao;

import com.smartlibrarypro.springbootlibrary.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // 🔍 Find books by partial title match (case-insensitive search if DB supports it)
    Page<Book> findByTitleContaining(@Param("title") String title, Pageable pageable);

    // 📚 Find books by category
    Page<Book> findByCategory(@Param("category") String category, Pageable pageable);

    // 📦 Find multiple books by a list of IDs
    @Query("SELECT b FROM Book b WHERE b.id IN :bookIds")
    List<Book> findBooksByBookIds(@Param("bookIds") List<Long> bookIds);
}
