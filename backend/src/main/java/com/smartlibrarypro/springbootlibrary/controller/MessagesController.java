package com.smartlibrarypro.springbootlibrary.controller;

import com.smartlibrarypro.springbootlibrary.entity.Message;
import com.smartlibrarypro.springbootlibrary.requestmodels.AdminQuestionRequest;
import com.smartlibrarypro.springbootlibrary.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:5173")  // Allow frontend to call APIs (if needed)
public class MessagesController {

    private final MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    // 🔐 POST message by user (authenticated)
    @PostMapping("/secure/add/message")
    public void postMessage(Authentication authentication,
                            @RequestBody Message messageRequest) {
        String userEmail = authentication.getName();
        messagesService.postMessage(messageRequest, userEmail);
    }

    // 🔐 Admin responds to a message
    @PutMapping("/secure/admin/message")
    public void putMessage(Authentication authentication,
                           @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail = authentication.getName();
        messagesService.putMessage(adminQuestionRequest, userEmail);
    }

    // 🔐 Get messages for authenticated user with pagination
    @GetMapping("/secure/user")
    public ResponseEntity<Page<Message>> getMessagesByUserEmail(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        String userEmail = authentication.getName(); // Extract user email from JWT
        Pageable pageable = PageRequest.of(page, size);
        Page<Message> messages = messagesService.findByUserEmail(userEmail, pageable);
        return ResponseEntity.ok(messages);
    }
}
