package com.luv2code.springbootlibrary.controller;

import com.luv2code.springbootlibrary.requestmodels.ReviewRequest;
import com.luv2code.springbootlibrary.service.ReviewService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(Authentication authentication,
                                    @RequestParam Long bookId) {
        String userEmail = authentication.getName();
        return reviewService.userReviewListed(userEmail, bookId);
    }

    @PostMapping("/secure")
    public void postReview(Authentication authentication,
                           @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = authentication.getName();
        reviewService.postReview(userEmail, reviewRequest);
    }
}