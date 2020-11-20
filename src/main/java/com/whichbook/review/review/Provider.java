package com.whichbook.review.review;

public interface Provider<T> {
    T requestById(Long id);
}
