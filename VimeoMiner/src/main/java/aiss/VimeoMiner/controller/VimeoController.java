package aiss.VimeoMiner.controller;

import aiss.VimeoMiner.model.*;
import aiss.VimeoMiner.service.*;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api.vimeo.com")
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
    VideoService videoService;

    //GET https://api.vimeo.com/channels
    @GetMapping("/channels")
    public List<ChannelVM> getAllChannels(){
        return channelService.getAllChannels();
    }

    //GET https://api.vimeo.com/channels/{id}
    @GetMapping("/channels/{id}")
    public ChannelVM getChannel (@PathVariable String id){
        ChannelVM channel = channelService.getChannel(id);
        return channel;
    }

    //GET https://api.vimeo.com/channels/{channel}/videos
    @GetMapping("/channels/{channel}/videos")
    public List<VideoVM> getAllVideosOfChannel (@PathVariable String channel) {
        List<VideoVM> videos = videoService.getAllVideosOfChannel(channel);
        return videos;
    }

    //GET https://api.vimeo.com/users
    @GetMapping("/users")
    public List<UserVM> getAllUsers(){
        return userService.getAllUsers();
    }

    //GET https://api.vimeo.com/users/{id}
    @GetMapping("/users/{id}")
    public UserVM getUser (@PathVariable String id) {
        UserVM video = userService.getUser(id);
        return video;
    }

    //GET https://api.vimeo.com/videos
    @GetMapping("/videos")
    public List<VideoVM> getAllVideos(){
        return videoService.getAllVideos();
    }

    //GET https://api.vimeo.com/videos/{id}
    @GetMapping("/videos/{id}")
    public VideoVM getVideo (@PathVariable String id){
        VideoVM video = videoService.getVideo(id);
        return video;
    }

    //GET https://api.vimeo.com/videos/{video}/comments
    @GetMapping("/videos/{video}/comments")
    public List<CommentVM> getAllCommentsOfVideo (@PathVariable String video){
        List<CommentVM> comments = commentService.getAllCommentsOfVideo(video);
        return comments;
    }

    //GET https://api.vimeo.com/videos/{video}/comments/{id}
    @GetMapping("/videos/{video}/comments/{id}")
    public CommentVM getComment (@PathVariable String video, @PathVariable String id){
        CommentVM comment = commentService.getComment(video, id);
        return comment;
    }

    //GET https://api.vimeo.com/videos/{video}/texttracks
    @GetMapping("/videos/{video}/texttracks")
    public List<TextTrackVM> getAllTextTracksOfVideo (@PathVariable String video){
        List<TextTrackVM> textTracks = textTrackService.getAllTextTracksOfVideo(video);
        return textTracks;
    }

    //GET https://api.vimeo.com/videos/{video}/texttracks/{id}
    @GetMapping("/videos/{video}/textracks/{id}")
    public TextTrackVM getTextTrack (@PathVariable String video, @PathVariable String id){
        TextTrackVM textTrack = textTrackService.getTextTrack(video, id);
        return textTrack;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/channels/{id}")
    public Channel create(String channelId) {
        ChannelVM channelVM = channelService.getChannel(channelId);
        Channel channel = convertChannelVMToChannel(channelVM);
        List<VideoVM> videosVimeo = videoService.getAllVideosOfChannel(channelId);
        List<Video> videos = new ArrayList<>();
        for (VideoVM videovm : videosVimeo) {
            Video v= convertVideoVMToVideo(videovm);
            videos.add(v);
        }
        channel.setVideos(videos);
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

    public static Channel convertChannelVMToChannel(ChannelVM c){
        String id= c.getId();
        String name=c.getName();
        String description=c.getDescription();
        String createdTime= c.getCreatedTime();
        Channel res= new Channel(id,name,description,createdTime);
        return res;
    }

}