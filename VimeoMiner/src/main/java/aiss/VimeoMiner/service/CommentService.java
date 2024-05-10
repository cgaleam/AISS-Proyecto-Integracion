package aiss.VimeoMiner.service;

import aiss.VimeoMiner.model.CommentVM;
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
public class CommentService{

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "17f1fa3527765a7c2f5c6f3c1317aef0";

    public CommentVM getComment(String video, String id) {
        CommentVM res = null;
        String uri = "https://api.vimeo.com/videos/{video}/comments/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<CommentVM> request = new HttpEntity<>(null, headers);

        ResponseEntity<CommentVM> response = restTemplate.exchange(uri, HttpMethod.GET, request, CommentVM.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<CommentVM> getAllCommentsOfVideo(String video) {
        List<CommentVM> res = new ArrayList<>();
        String uri = "https://api.vimeo.com/videos/{video}/comments";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<CommentVM> request = new HttpEntity<>(null, headers);

        ResponseEntity<CommentVM> response = restTemplate.exchange(uri, HttpMethod.GET, request, CommentVM.class);

        if(response.getBody() != null){
            res.add(response.getBody());
        }
        return res;
    }

//    public User getCommentAuthor(String video, String id) {
//        User res = null;
//        String uri = "https://api.vimeo.com/videos/{video}/comments/{id}";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer" + TOKEN);
//        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
//
//        ResponseEntity<Comment> response = restTemplate.exchange(uri, HttpMethod.GET, request, Comment.class);
//
//        if(response.getBody() != null){
//            res = response.getBody().getUser();
//        }
//        return res;
//    }
}
