package com.whichbook.review.review;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class BookProvider extends AbstractProvider<BookDto>{

    protected BookProvider(RestTemplate restTemplate, @Value("${book.request.url}") String requestUrl) {
        super(restTemplate, requestUrl);
    }

    @Override
    public BookDto requestById(Long id) {
        return null;
    }
}
