package com.whichbook.review.review;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final Provider<UserDto> userProvider;
    private final Provider<BookDto> bookProvider;

    public ReviewService(ReviewRepository reviewRepository, Provider<UserDto> userProvider, Provider<BookDto> bookProvider) {
        this.reviewRepository = reviewRepository;
        this.userProvider = userProvider;
        this.bookProvider = bookProvider;
    }

    public List<ReviewResponseDto> getLatestReviews() {
        List<Review> reviews = reviewRepository.findTop9ByCreatedAtOrderByCreatedAtDesc(LocalDateTime.now());

        return reviews.stream().map(review -> {
            UserDto userDto = userProvider.requestById(review.getUserId());
            BookDto bookDto = bookProvider.requestById(review.getBookId());
            return ReviewResponseDto.of(review, bookDto, userDto);

        }).collect(Collectors.toList());
    }

}
