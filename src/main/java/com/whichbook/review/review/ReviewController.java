package com.whichbook.review.review;

import com.whichbook.review.review.dto.ReviewResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review/latest")
    public ResponseEntity<?> getReviewLatest() {
        return ResponseEntity.ok(reviewService.getLatestReviews());
    }

    @PostMapping("/review")
    public ResponseEntity<?> createReview(@Validated @RequestBody ReviewRequestDto reviewRequestDto) {
        URI uri = reviewService.createReview(reviewRequestDto);
        return ResponseEntity.created(uri).body(uri.toString());
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<?> retrieveReview(@PathVariable Long id) {
        ReviewResponseDto reviewResponseDto = reviewService.retrieveReview(id);
        return ResponseEntity.ok(reviewResponseDto);
    }


}
