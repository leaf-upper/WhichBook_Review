package com.whichbook.review.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewRequestDto {
    private Long bookId;
    private Long userId;
    private String title;
    private String description;

    public Review toReview() {
        return Review.builder()
                .bookId(bookId)
                .userId(userId)
                .title(title)
                .description(description)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();
    }
}
