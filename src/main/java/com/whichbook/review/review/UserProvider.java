package com.whichbook.review.review;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserProvider extends AbstractProvider<UserDto>{

    protected UserProvider(RestTemplate restTemplate, @Value("${user.request_url}") String requestUrl) {
        super(restTemplate, requestUrl);
    }

    @Override
    public UserDto requestById(Long id) {
        return null;
    }
}
