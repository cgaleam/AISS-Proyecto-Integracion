package aiss.videominer.controller;

import aiss.videominer.exceptions.CaptionNotFoundException;
import aiss.videominer.exceptions.ChannelNotFoundException;
import aiss.videominer.exceptions.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/captions")
public class CaptionController {

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    CaptionRepository captionRepository;

    // GET http://localhost:8080/videominer/captions
    public List<Caption> findAll() { return captionRepository.findAll(); }

    // GET http://localhost:8080/videominer/videos/{videoId}/captions
    public List<Caption> getAllCaptionByVideoId(@PathVariable String videoId, @PathVariable String channelId)
            throws VideoNotFoundException, ChannelNotFoundException {
        List<Caption> res= new ArrayList<>();
        Optional<Channel> channel= channelRepository.findById(String.valueOf(channelId));
        if(channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(String.valueOf(videoId));

            if (video.isPresent()) {
                res = video.get().getCaptions();
            } else {
                throw new VideoNotFoundException();
            }

        }else {
            throw new ChannelNotFoundException();
        }
        return res;
    }

    // GET http://localhost:8080/videominer/captions/{id}
    @GetMapping("/{id}")
    public Caption findOne(@PathVariable String channelId, @PathVariable String videoId, @PathVariable String captionId)
            throws CaptionNotFoundException, VideoNotFoundException, ChannelNotFoundException {
        Caption res= null;
        Optional<Channel> channel= channelRepository.findById(String.valueOf(channelId));
        if(channel.isPresent()){
            Optional<Video> video= videoRepository.findById(String.valueOf(videoId));

            if(video.isPresent()){
                Optional<Caption> caption= captionRepository.findById(String.valueOf(captionId));
                if(caption.isEmpty()){
                    throw new CaptionNotFoundException();
                }
                res=caption.get();

            }else{
                throw new VideoNotFoundException();
            }
        }else {
            throw new ChannelNotFoundException();
        }
        return res;
    }
}
