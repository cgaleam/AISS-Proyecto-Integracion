package aiss.VimeoMiner.controller;

import aiss.VimeoMiner.model.*;
import aiss.VimeoMiner.service.*;
import aiss.videominer.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
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
    final String videoMinerUri = "http://localhost:8080/videominer/videos";

    //GET https://localhost:8081/vimeominer/{id}
    @GetMapping("/{id}")
    public ChannelVM getChannel (@PathVariable String id){
        ChannelVM channel = channelService.getChannel(id);
        return channel;
    }

    public static Video convertVideoVMToVideo(VideoVM v){
        String id=v.getId();
        String name=v.getName();
        String description= v.getDescription();
        String releaseTime= v.getReleaseTime();
        Video res= new Video(id,name,description,releaseTime);
        return res;
    }

    public static List<Video> convertVideos(List<VideoVM> videos){
        List<Video> res = new ArrayList<>();
        for (VideoVM v : videos){
            String id=v.getId();
            String name=v.getName();
            String description= v.getDescription();
            String releaseTime= v.getReleaseTime();
            Video nuevo= new Video(id,name,description,releaseTime);
            res.add(nuevo);
        }
        return res;
    }

    public static Channel convertChannelVMToChannel(ChannelVM c){
        String name=c.getName();
        String description=c.getDescription();
        String createdTime= c.getCreatedTime();
        Channel res= new Channel(name,description,createdTime);
        return res;
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/{id}")
//    public Channel sendChannel(@PathVariable String id){
//        ChannelVM canal = channelService.getChannel(id);
//        Channel canalParseado = convertChannelVMToChannel(canal);
//        HttpEntity<Channel> request = new HttpEntity<>(canalParseado);
//        ResponseEntity<Channel> response = restTemplate.exchange(videoMinerUri, HttpMethod.POST,request, Channel.class);
//        return response.getBody();
//

}