package com.whichbook.review.review.provider;


import com.whichbook.review.aop.TokenNotValidException;
import com.whichbook.review.review.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class UserProvider extends AbstractProvider<UserDto>{

    protected UserProvider(RestTemplate restTemplate, @Value("${user.request.url}") String requestUrl) {
        super(restTemplate, requestUrl);
    }

    @Override
    public UserDto requestById(Long id) {
        return null;
    }

    public void authorizeByToken(String token) {
        String url = requestUrl + "/auth";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token);

        try {
            ResponseEntity<String> responseEntity =
                    restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(httpHeaders), String.class);
        }catch (HttpClientErrorException ex) {
            throw new TokenNotValidException(ex.getMessage());
        }
    }


}
