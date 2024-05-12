package aiss.VimeoMiner.controller;

import aiss.VimeoMiner.model.*;
import aiss.VimeoMiner.service.*;
import aiss.videominer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vimeominer")
public class VimeoController {

    @Autowired
    ChannelService channelService;
    @Autowired
    CommentService commentService;
    @Autowired
    TextTrackService textTrackService;
    @Autowired
    UserService userService;
    @Autowired
    static VideoService videoService;

    @Autowired
    RestTemplate restTemplate;
    final String videoMinerUri = "http://localhost:8080/videominer/channels";

    //GET https://localhost:8081/vimeominer/{id}
    @GetMapping("/{id}")
    public Channel getChannel (@PathVariable String id){
        ChannelVM channel = channelService.getChannel(id);
        Channel res = convertChannelVMToChannel(channel);
        return res;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public Channel sendChannel(@PathVariable String id) {
        ChannelVM canal = channelService.getChannel(id);
        Channel canalParseado = convertChannelVMToChannel(canal);
        HttpEntity<Channel> request = new HttpEntity<>(canalParseado);
        ResponseEntity<Channel> response = restTemplate.exchange(videoMinerUri, HttpMethod.POST, request, Channel.class);
        return response.getBody();
    }

    public static List<Comment> convertComments(List<CommentVM> comments){
        List<Comment> res = new ArrayList<>();
        for (CommentVM c : comments){
            User author = convertUser(c.getUser());
            Comment nuevo = new Comment();
            nuevo.setId(c.getId());
            nuevo.setText(c.getText());
            nuevo.setCreatedOn(c.getCreatedOn());
            nuevo.setAuthor(author);
            res.add(nuevo);
        }
        return res;
    }

    private static User convertUser(UserVM u) {
        Long id = Long.parseLong(u.getId()) ;
        User nuevo = new User();
        nuevo.setId(id);
        nuevo.setName(u.getName());
        nuevo.setUser_link(u.getUserLink());
        nuevo.setPicture_link(u.getPictureLink());
        return nuevo;
    }

    public static List<Caption> convertTextTracks(List<TextTrackVM> tt){
        List<Caption> res = new ArrayList<>();
        for (TextTrackVM t : tt){
            Caption nuevo = new Caption();
            nuevo.setId(t.getId());
            nuevo.setName(t.getName());
            nuevo.setLanguage(t.getLanguage());
            res.add(nuevo);
        }
        return res;
    }
    public static List<Video> convertVideos(List<VideoVM> videos){
        List<Video> res = new ArrayList<>();
        for (VideoVM v : videos){
            Video nuevo= new Video();
            nuevo.setId(v.getId());
            nuevo.setName(v.getName());
            nuevo.setDescription(v.getDescription());
            nuevo.setReleaseTime(v.getReleaseTime());
            nuevo.setComments(convertComments(v.getComments()));
            nuevo.setCaptions(convertTextTracks(v.getTextTracks()));
            res.add(nuevo);
        }
        return res;
    }

    public static Channel convertChannelVMToChannel(ChannelVM c){
        String id = c.getId();
        String name=c.getName();
        String description=c.getDescription();
        String createdTime= c.getCreatedTime();
        Channel res= new Channel(id,name,description,createdTime);
        res.setVideos(convertVideos(c.getVideos()));
        return res;
    }
}