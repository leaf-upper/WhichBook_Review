package com.whichbook.review.review;

import org.springframework.web.client.RestTemplate;

abstract public class AbstractProvider<T> implements Provider<T> {
    private final RestTemplate restTemplate;
    private final String requestUrl;

    protected AbstractProvider(RestTemplate restTemplate, String requestUrl) {
        this.restTemplate = restTemplate;
        this.requestUrl = requestUrl;
    }

}