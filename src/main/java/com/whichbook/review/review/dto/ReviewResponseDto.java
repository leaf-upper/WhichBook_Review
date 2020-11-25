package com.whichbook.review.review.dto;

import com.whichbook.review.review.Review;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data @Builder
public class ReviewResponseDto {

    private String author;

    private String bookTitle;

    private String reviewTitle;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ReviewResponseDto of(Review review, BookDto bookDto, UserDto userDto) {
        return ReviewResponseDto.builder()
                .author(userDto.getNickname())
                .bookTitle(bookDto.getTitle())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .reviewTitle(review.getTitle())
                .description(review.getDescription())
                .build();

    }
}
