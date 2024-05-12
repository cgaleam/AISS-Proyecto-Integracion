package aiss.YouTubeMiner.services;

import aiss.YouTubeMiner.model.youtube.caption.CaptionSearch;
import aiss.YouTubeMiner.model.youtube.comment.Comment;
import aiss.YouTubeMiner.model.youtube.comment.CommentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    private static final String token = "AIzaSyCRsJxwUMQ3wg61XvBpfCn5IQlC_cXSQXg";

    public List<Comment> findComments(String videoId, Integer maxComments){
        try{
            String uri = "https://youtube.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId=" + videoId+"&maxResults="+maxComments+"&key=" + token;
            HttpHeaders headers= new HttpHeaders();
            headers.set("X-goog-api-key", token);

            HttpEntity<CommentSearch> request = new HttpEntity<>(null,headers);
            ResponseEntity<CommentSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request, CommentSearch.class);

            if(response.hasBody()) {
                return response.getBody().getItems();
            }else{
                return new LinkedList<>();
            }
        }catch (Exception e){
            if(e.getMessage().contains("403")){
                return new LinkedList<>();
            }else{
                throw e;
            }

        }

        }
    }
