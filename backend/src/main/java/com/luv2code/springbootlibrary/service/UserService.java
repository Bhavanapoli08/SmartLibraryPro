package com.luv2code.springbootlibrary.service;

import com.luv2code.springbootlibrary.dao.UserRepository;
import com.luv2code.springbootlibrary.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, email, encodedPassword,"USER");
        return userRepository.save(user);
    }
}
