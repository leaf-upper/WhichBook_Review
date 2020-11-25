package com.whichbook.review.review;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter @Builder
@Entity
public class Review {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private Long userId;

    private String title;

    @Lob
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
