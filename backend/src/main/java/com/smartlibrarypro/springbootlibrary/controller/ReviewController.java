package com.smartlibrarypro.springbootlibrary.controller;

import com.smartlibrarypro.springbootlibrary.requestmodels.ReviewRequest;
import com.smartlibrarypro.springbootlibrary.service.ReviewService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173") // Ensure CORS is allowed for frontend calls
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 🔐 Check if a user has reviewed a book
    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(Authentication authentication,
                                    @RequestParam Long bookId) {
        String userEmail = authentication.getName();
        return reviewService.userReviewListed(userEmail, bookId);
    }

    // 🔐 Post a new review for a book
    @PostMapping("/secure")
    public void postReview(Authentication authentication,
                           @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = authentication.getName();
        reviewService.postReview(userEmail, reviewRequest);
    }
}
