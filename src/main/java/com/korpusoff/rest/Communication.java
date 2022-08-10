package com.korpusoff.rest;

import com.korpusoff.rest.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Component
public class Communication {

    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private final String URL = "http://94.198.50.185:7081/api/users";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
        this.headers.set("Cookie", String.join(";", restTemplate.headForHeaders(URL).get("Set-Cookie")));
    }

//    public List<User> getAllUsers() {
//        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null
//                , new ParameterizedTypeReference<List<User>>() {
//                });
//        System.out.println(responseEntity.getHeaders());
//        return responseEntity.getBody();
//    }

    public ResponseEntity<String> saveUser() {
        User user = new User((long) 3, "James", "Brown", (byte) 48);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        return restTemplate.postForEntity(URL, httpEntity, String.class);
    }

    public ResponseEntity<String> updateUser() {
        User user = new User((long) 3, "Thomas", "Shelby", (byte) 48);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class, 3);
    }

    private ResponseEntity<String> deleteUser() {
        Map<String, Long> map = new HashMap<>() {{
            put("id", (long) 3);
        }};
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(URL + "/{id}", HttpMethod.DELETE, httpEntity, String.class, map);
    }

    public StringBuilder code() {
        StringBuilder sb = new StringBuilder();
        sb.append(saveUser().getBody());
        sb.append(updateUser().getBody());
        sb.append(deleteUser().getBody());
        return sb;
    }
}
