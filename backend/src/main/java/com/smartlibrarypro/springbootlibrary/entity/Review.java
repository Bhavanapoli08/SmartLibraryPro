package com.smartlibrarypro.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "date", updatable = false)
    @CreationTimestamp
    private Date date;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "review_description", columnDefinition = "TEXT")
    private String reviewDescription;
}
