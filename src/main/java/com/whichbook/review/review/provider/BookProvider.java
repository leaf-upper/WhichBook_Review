package com.whichbook.review.review.provider;

import com.whichbook.review.review.dto.BookDto;
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
        String url = requestUrl + "/book/{bookId}";
        return restTemplate.getForObject(url, BookDto.class, id);
    }
}
