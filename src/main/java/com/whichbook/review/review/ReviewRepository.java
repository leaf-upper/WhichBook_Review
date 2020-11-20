package com.whichbook.review.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findTop9ByCreatedAtOrderByCreatedAtDesc(LocalDateTime localDateTime);
}
