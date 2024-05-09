package aiss.VimeoMiner.service;

import aiss.VimeoMiner.model.Channel;
import aiss.VimeoMiner.model.User;
import aiss.VimeoMiner.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "17f1fa3527765a7c2f5c6f3c1317aef0";

    public Video getVideo(String id) {
        Video res = null;
        String uri = "https://api.vimeo.com/videos/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<Video> request = new HttpEntity<>(null, headers);

        ResponseEntity<Video> response = restTemplate.exchange(uri, HttpMethod.GET, request, Video.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<Video> getAllVideos() {
        List<Video> res = new ArrayList<>();
        String uri = "https://api.vimeo.com/videos";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<Video> request = new HttpEntity<>(null, headers);

        ResponseEntity<Video> response = restTemplate.exchange(uri, HttpMethod.GET, request, Video.class);

        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }

    public List<Video> getVideosOfChannel(String channel) {
        List<Video> res = new ArrayList<>();
        String uri = "https://api.vimeo.com/channels/{channel}/videos";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<Video> request = new HttpEntity<>(null, headers);

        ResponseEntity<Video> response = restTemplate.exchange(uri, HttpMethod.GET, request, Video.class);

        if(response.getBody() != null){
            res.add(response.getBody());
        }
        return res;
    }
}
