package aiss.YouTubeMiner.services;

import aiss.YouTubeMiner.model.youtube.channel.Channel;
import aiss.YouTubeMiner.model.youtube.channel.ChannelSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    private static final String token = "AIzaSyCRsJxwUMQ3wg61XvBpfCn5IQlC_cXSQXg";

    public Channel findChannel(String id){
        String uri = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet&id=" + id + "&key=" + token;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers= new HttpHeaders();
        headers.set("X-goog-api-key", token);
        HttpEntity<ChannelSearch> request = new HttpEntity<>(null,headers);
        ResponseEntity<ChannelSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request, ChannelSearch.class);
        assert response.getBody() != null;
        return response.getBody().getItems().get(0);
    }

}
