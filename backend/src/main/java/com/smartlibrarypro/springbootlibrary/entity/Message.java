package com.smartlibrarypro.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    // ✅ Custom constructor used in service or controller when creating a new message
    public Message(String title, String question) {
        this.title = title;
        this.question = question;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "admin_email")
    private String adminEmail;

    @Column(name = "response", columnDefinition = "TEXT")
    private String response;

    @Column(name = "closed")
    private boolean closed = false;
}














