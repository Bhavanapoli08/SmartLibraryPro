package com.smartlibrarypro.springbootlibrary.dao;

import com.smartlibrarypro.springbootlibrary.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // ✅ Fetch messages by user email
    Page<Message> findByUserEmail(String userEmail, Pageable pageable);

    // ✅ Fetch messages based on 'closed' status
    Page<Message> findByClosed(boolean closed, Pageable pageable);
}

