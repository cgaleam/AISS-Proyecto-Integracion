package aiss.videominer.controller;

import aiss.VimeoMiner.controller.VimeoController;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/videos")
public class VideoController {

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    RestTemplate restTemplate;

    // GET http://localhost:8080/videominer/videos
    public List<Video> getAllVideoByChannelId(@PathVariable(value = "channelId") long channelId) {
        Optional<Channel> channel = channelRepository.findById(String.valueOf(channelId));
        List<Video> videos = new ArrayList<>(channel.get().getVideos());
        return videos;
    }

    // GET http://localhost:8080/videominer/videos/{id}
    @GetMapping("/{id}")
    public Video findOne(@PathVariable long id) {
        Optional<Video> video = videoRepository.findById(String.valueOf(id));
        return video.get();
    }

    //POST http://localhost:8080/videominer/channels
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel postChannel(@Valid @RequestBody String id) {
        Channel channel = VimeoController.getConvertedChannel(id);
        String uri = "http://localhost:8080/videominer/channels";
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Channel> request = new HttpEntity<>(channel, httpHeaders);
        ResponseEntity<Channel> response = restTemplate.exchange(uri, HttpMethod.POST, request, Channel.class);
        return response.getBody();
    }
}
