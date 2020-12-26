package com.whichbook.review.review;

import com.whichbook.review.aop.IsAuthenticated;
import com.whichbook.review.review.dto.BookDto;
import com.whichbook.review.review.dto.ReviewResponseDto;
import com.whichbook.review.review.dto.UserDto;
import com.whichbook.review.review.provider.Provider;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
        List<Review> reviews = reviewRepository.findTopByCreatedAtBeforeOrderByCreatedAt(LocalDateTime.now());

        return reviews.stream().map(review -> {
            UserDto userDto = userProvider.requestById(review.getUserId());
            BookDto bookDto = bookProvider.requestById(review.getBookId());
            return ReviewResponseDto.of(review, bookDto, userDto);

        }).collect(Collectors.toList());
    }

    @IsAuthenticated
    public URI createReview(ReviewRequestDto reviewRequestDto) {
        Review review = reviewRequestDto.toReview();
        review = reviewRepository.save(review);

        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(review.getId());
    }

    public ReviewResponseDto retrieveReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        UserDto userDto = userProvider.requestById(review.getUserId());
        BookDto bookDto = bookProvider.requestById(review.getBookId());
        return ReviewResponseDto.of(review, bookDto, userDto);
    }
}
