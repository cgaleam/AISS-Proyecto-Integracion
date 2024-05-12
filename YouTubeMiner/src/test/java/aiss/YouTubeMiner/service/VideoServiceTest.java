package aiss.YouTubeMiner.service;

import aiss.YouTubeMiner.model.youtube.videoSnippet.VideoSnippet;
import aiss.YouTubeMiner.services.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VideoServiceTest {
    @Autowired
    VideoService videoService;
    @Test
    void findVideo(){
        String id = "YDTANv-7k7g";
        VideoSnippet c = videoService.findVideo(id);
        System.out.println(c);
    }

}