package aiss.YouTubeMiner.controller;

import aiss.YouTubeMiner.etl.TransformCaption;
import aiss.YouTubeMiner.etl.TransformChannel;
import aiss.YouTubeMiner.etl.TransformComment;
import aiss.YouTubeMiner.etl.TransformVideo;
import aiss.YouTubeMiner.exception.MaxValueException;
import aiss.YouTubeMiner.model.videominer.Caption;
import aiss.YouTubeMiner.model.videominer.Channel;
import aiss.YouTubeMiner.model.videominer.Comment;
import aiss.YouTubeMiner.model.videominer.Video;
import aiss.YouTubeMiner.model.youtube.channel.ChannelYouTube;
import aiss.YouTubeMiner.service.CaptionService;
import aiss.YouTubeMiner.service.ChannelService;
import aiss.YouTubeMiner.service.CommentService;
import aiss.YouTubeMiner.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
@Tag(name= "Youtube", description =  "Youtube management API")
@RestController
@RequestMapping("/api/youtube/v3/channels")
public class ChannelController {

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
    
    @Operation(
            summary = "Retrieve a Youtube channel by id",
            description= "Get a Youtube channel by specifying its id",
            tags = {"channel", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Youtube channel", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
            })

    @GetMapping("/{id}")
    public Channel findChannel(@Parameter(description = "User's id of the channel ") @PathVariable String id,
                               @Parameter(description = "Optional parameter to limit the number of videos")@RequestParam(required = false, defaultValue = "10") Integer sizeVideo,
                               @Parameter(description = "Optional parameter to limit the number of comments")@RequestParam(required = false, defaultValue = "10") Integer sizeComment
                            ) throws MaxValueException {
        if(sizeComment < 0 || sizeVideo <0){
            throw new MaxValueException();
        }
        ChannelYouTube channelYoutube = channelService.findChannel(id);
        Channel channel= TransformChannel.transformChannel(channelYoutube);

        Integer maxVideo = sizeVideo==null? 10 : sizeVideo;
        Integer maxComment = sizeComment==null? 10: sizeComment;

        List<AISS.YouTubeMiner.model.youtube.videoSnippet.VideoSnippet> videosYoutube = videoService.findVideos(id,maxVideo);
        List<Video> videos = new LinkedList<>();

        for(AISS.YouTubeMiner.model.youtube.videoSnippet.VideoSnippet videoYoutube:videosYoutube){
            Video video= TransformVideo.transformVideo(videoYoutube);
            List<Comment> comentarios = commentService.findCommentsFromVideoId(videoYoutube.getSnippet().getResourceId().getVideoId(), maxComment ).stream().map(TransformComment::transformComment).toList();
            video.setComments(comentarios);
            List<Caption> captions= captionService.findCaptionsFromVideo(videoYoutube.getSnippet().getResourceId().getVideoId()).stream().map(TransformCaption::transformCaption).toList();
            video.setCaptions(captions);
            videos.add(video);
        }
        channel.setVideos(videos);
        return channel;
    }
    @Operation(
            summary = "Insert a Channel in VideoMiner",
            description= "Add a new Youtube channel (looked by its id in YouTube) whose data is passed in the body of the request in Json format",
            tags = {"channels", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Youtube channel", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
            })
    @PostMapping("/{id}")
    public Channel postChannel(@Parameter(description = "User's id of the channel")@PathVariable String id,
                                @Parameter(description = "Optional parameter to limit the number of videos")@RequestParam(required = false, defaultValue = "10") Integer sizeVideo,
                               @Parameter(description = "Optional parameter to limit the number of comments")@RequestParam(required = false, defaultValue = "10") Integer sizeComment
    ) throws MaxValueException {
        if(sizeComment < 0 || sizeVideo <0){
            throw new MaxValueException();
        }
        Integer maxVideo = sizeVideo==null? 10 : sizeVideo;
        Integer maxComment = sizeComment==null? 10: sizeComment;
        Channel channel=findChannel(id, maxVideo, maxComment);
        //String uri = "http://localhost:8080/videominer/channels";
        String uri = "https://videominer.azurewebsites.net/videominer/channels";

        System.out.println(channel);


        HttpHeaders headers= new HttpHeaders();

        HttpEntity<Channel> request = new HttpEntity<>(channel,headers);
        ResponseEntity<Channel> response = restTemplate.exchange(uri, HttpMethod.POST, request, Channel.class);
        return response.getBody();
    }
 
}