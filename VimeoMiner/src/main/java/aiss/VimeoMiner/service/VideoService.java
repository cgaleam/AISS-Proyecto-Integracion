package aiss.VimeoMiner.service;

import aiss.VimeoMiner.model.VideoVM;
import aiss.VimeoMiner.model.VideoVMList;
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

    public VideoVM getVideo(String id) {
        VideoVM res = null;
        String uri = "https://api.vimeo.com/videos/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + TOKEN);
        HttpEntity<VideoVM> request = new HttpEntity<>(null, headers);

        ResponseEntity<VideoVM> response = restTemplate.exchange(uri, HttpMethod.GET, request, VideoVM.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

//    public List<VideoVM> getAllVideos() {
//        List<VideoVM> res = new ArrayList<>();
//        String uri = "https://api.vimeo.com/videos";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + TOKEN);
//        HttpEntity<VideoVM> request = new HttpEntity<>(null, headers);
//
//        ResponseEntity<VideoVM> response = restTemplate.exchange(uri, HttpMethod.GET, request, VideoVM.class);
//
//        if (response.getBody() != null) {
//            res.add(response.getBody());
//        }
//        return res;
//    }

    public List<VideoVM> getAllVideosOfChannel(String channel) {
        List<VideoVM> res = new ArrayList<>();
        String uri = "https://api.vimeo.com/channels/" + channel + "/videos";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + TOKEN);
        HttpEntity<VideoVMList> request = new HttpEntity<>(null, headers);

        ResponseEntity<VideoVMList> response = restTemplate.exchange(uri, HttpMethod.GET, request, VideoVMList.class);

        assert response.getBody() != null;
        res = response.getBody().getData();
        return res;
    }
}
