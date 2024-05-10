package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
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
    VideoRepository videoRepository;
    @Autowired
    CaptionRepository captionRepository;

    // GET http://localhost:8080/videominer/captions
    public List<Caption> findAll(){
        return  captionRepository.findAll();
    }

    // GET http://localhost:8080/videominer/captions/{id}
    @GetMapping("/{id}")
    public Caption findOne(@PathVariable long id) {
        Optional<Caption> caption = captionRepository.findById(String.valueOf(id));
        return caption.get();
    }

    // GET http://localhost:8080/videominer/videos/{id}
    public List<Caption> getAllCaptionByVideoId(@PathVariable(value = "videoId") long videoId) {
        Optional<Video> video = videoRepository.findById(String.valueOf(videoId));
        List<Caption> captions = new ArrayList<>(video.get().getCaptions());
        return captions;
    }

}
