package aiss.YouTubeMiner.services;

import aiss.YouTubeMiner.model.youtube.caption.Caption;
import aiss.YouTubeMiner.model.youtube.caption.CaptionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    private static final String token = "AIzaSyCRsJxwUMQ3wg61XvBpfCn5IQlC_cXSQXg";

    public List<Caption> findCaptions(String videoId){
        String uri = "https://www.googleapis.com/youtube/v3/captions?part=snippet&videoId="+videoId;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-goog-api-key", token);
        HttpEntity<CaptionSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<CaptionSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request, CaptionSearch.class);
        return response.getBody().getItems();
    }
}