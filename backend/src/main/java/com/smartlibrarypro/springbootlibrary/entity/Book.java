package com.smartlibrarypro.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "copies", nullable = false)
    private int copies;

    @Column(name = "copies_available", nullable = false)
    private int copiesAvailable;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "img")
    private String img;  // URL or Base64 encoded image
}


