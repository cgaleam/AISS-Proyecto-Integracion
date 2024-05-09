package aiss.VimeoMiner.service;

import aiss.VimeoMiner.model.Comment;
import aiss.VimeoMiner.model.TextTrack;
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
public class TextTrackService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "17f1fa3527765a7c2f5c6f3c1317aef0";

    public TextTrack getTextTrack(String video, String id) {
        TextTrack res = null;
        String uri = "https://api.vimeo.com/videos/{video}/texttracks/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<TextTrack> request = new HttpEntity<>(null, headers);

        ResponseEntity<TextTrack> response = restTemplate.exchange(uri, HttpMethod.GET, request, TextTrack.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<TextTrack> getAllCommentsOfVideo(String video) {
        List<TextTrack> res = new ArrayList<>();
        String uri = "https://api.vimeo.com/videos/{video}/texttracks";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<TextTrack> request = new HttpEntity<>(null, headers);

        ResponseEntity<TextTrack> response = restTemplate.exchange(uri, HttpMethod.GET, request, TextTrack.class);

        if(response.getBody() != null){
            res.add(response.getBody());
        }
        return res;
    }
}
