package com.whichbook.review.review.provider;

import org.springframework.web.client.RestTemplate;

abstract public class AbstractProvider<T> implements Provider<T> {
    protected final RestTemplate restTemplate;
    protected final String requestUrl;

    protected AbstractProvider(RestTemplate restTemplate, String requestUrl) {
        this.restTemplate = restTemplate;
        this.requestUrl = requestUrl;
    }

}