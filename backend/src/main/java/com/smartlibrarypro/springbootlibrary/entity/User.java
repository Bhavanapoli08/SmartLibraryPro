package com.smartlibrarypro.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;  // e.g. "USER", "ADMIN"

    // 🔒 Optional future enhancements:
    // private boolean enabled = true;
    // private boolean accountNonLocked = true;
}
