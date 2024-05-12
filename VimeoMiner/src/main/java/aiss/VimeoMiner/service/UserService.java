package aiss.VimeoMiner.service;


import aiss.VimeoMiner.model.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@ComponentScan
@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "17f1fa3527765a7c2f5c6f3c1317aef0";

    public UserVM getUser(String id) {
        UserVM res = null;
        String uri = "https://api.vimeo.com/users/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + TOKEN);
        HttpEntity<UserVM> request = new HttpEntity<>(null, headers);

        ResponseEntity<UserVM> response = restTemplate.exchange(uri, HttpMethod.GET, request, UserVM.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<UserVM> getAllUsers() {
        List<UserVM> res = new ArrayList<>();
        String uri = "https://api.vimeo.com/users";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + TOKEN);
        HttpEntity<UserVM> request = new HttpEntity<>(null, headers);

        ResponseEntity<UserVM> response = restTemplate.exchange(uri, HttpMethod.GET, request, UserVM.class);

        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }
}
