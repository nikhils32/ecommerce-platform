package com.productservice.productservice.security;

import com.productservice.userservice.model.SessionStatus;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class TokenValidator {
    private RestTemplateBuilder restTemplateBuilder;

    TokenValidator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    /**
     * This method should call the UserService to validate the token.
     * If the token is valid then return the corresponding object else
     * return empty.
     * @param token
     * @return
     */

    public ResponseEntity<SessionStatus> validateToken(String token) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        //Make an HTTP call to UserService to call the validateToken method.

        String url = "http://localhost:8080/userservice/validateToken?token=" + token;

        // Assuming the validateToken endpoint returns a SessionStatus
        return restTemplate.getForEntity(url, SessionStatus.class);

        //return Optional.empty();
    }
}
