package aiss.YouTubeMiner.controller;

import aiss.YouTubeMiner.model.youtube.caption.Caption;
import aiss.YouTubeMiner.model.youtube.channel.Channel;
import aiss.YouTubeMiner.model.youtube.comment.Comment;
import aiss.YouTubeMiner.model.youtube.videoSnippet.VideoSnippet;
import aiss.YouTubeMiner.services.CaptionService;
import aiss.YouTubeMiner.services.ChannelService;
import aiss.YouTubeMiner.services.CommentService;
import aiss.YouTubeMiner.services.VideoService;

import aiss.YouTubeMiner.transformations.CaptionTransform;
import aiss.YouTubeMiner.transformations.ChannelTransform;
import aiss.YouTubeMiner.transformations.CommentTransform;
import aiss.YouTubeMiner.transformations.VideoTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import aiss.YouTubeMiner.model.videoMiner.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/youtube/v3/channels")
public class YoutubeController {

    @Autowired
    ChannelService channelService;
    @Autowired
    VideoService videoService;
    @Autowired
    CommentService commentService;
    @Autowired
    CaptionService captionService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{id}")
    public ChannelVideo findChannel(@PathVariable String id) {
        Channel channelYoutube = channelService.getChannel(id);
        ChannelVideo channel = ChannelTransform.channelTransform(channelYoutube);



        List<VideoSnippet> videosYoutube = videoService.getVideos(id);
        List<VideoVideo> videos = new LinkedList<>();

        for(VideoSnippet videoYoutube:videosYoutube){
            VideoVideo video= VideoTransform.videoTransform(videoYoutube);
            List<CommentVideo> comentarios = commentService.getComments(videoYoutube.getSnippet().getResourceId().getVideoId() ).stream().map(CommentTransform::commentTransform).toList();
            video.setComments(comentarios);
            List<CaptionVideo> captions= captionService.getCaptions(videoYoutube.getSnippet().getResourceId().getVideoId()).stream().map(CaptionTransform::captionTransform).toList();
            video.setCaptions(captions);
            videos.add(video);
        }
        channel.setVideos(videos);
        return channel;
    }

    @PostMapping("/{id}")
    public ChannelVideo postChannel(@PathVariable String id){
        ChannelVideo channel = findChannel(id);

        String uri = "http://localhost:8080/videominer/channels";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ChannelVideo> request = new HttpEntity<>(channel, headers);
        ResponseEntity<ChannelVideo> response = restTemplate.exchange(uri, HttpMethod.POST, request, ChannelVideo.class);
        return response.getBody();
    }
    }

