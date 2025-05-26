package com.luv2code.springbootlibrary.controller;

import com.luv2code.springbootlibrary.entity.Message;
import com.luv2code.springbootlibrary.requestmodels.AdminQuestionRequest;
import com.luv2code.springbootlibrary.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private final MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/secure/add/message")
    public void postMessage(Authentication authentication,
                            @RequestBody Message messageRequest) {
        String userEmail = authentication.getName();
        messagesService.postMessage(messageRequest, userEmail);
    }

    @PutMapping("/secure/admin/message")
    public void putMessage(Authentication authentication,
                           @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail = authentication.getName();
        messagesService.putMessage(adminQuestionRequest, userEmail);
    }

    @GetMapping("/secure/user")
    public ResponseEntity<Page<Message>> getMessagesByUserEmail(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        String userEmail = authentication.getName(); // Extracted from JWT
        Pageable pageable = PageRequest.of(page, size);
        Page<Message> messages = messagesService.findByUserEmail(userEmail, pageable);
        return ResponseEntity.ok(messages);
    }


}