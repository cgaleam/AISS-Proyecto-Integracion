package aiss.VimeoMiner.controller;

import aiss.VimeoMiner.model.*;
import aiss.VimeoMiner.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //POST https://api.vimeo.com/videos
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public VMVideo create(@Valid @RequestBody VMVideo VMVideo) {
//        VMVideo video = videoService.save(new VMVideo());
//        return video;
//    }

}