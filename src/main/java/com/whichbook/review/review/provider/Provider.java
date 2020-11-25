package com.whichbook.review.review.provider;

public interface Provider<T> {
    T requestById(Long id);
}
