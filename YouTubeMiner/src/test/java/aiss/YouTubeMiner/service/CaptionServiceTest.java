package aiss.YouTubeMiner.service;

import aiss.YouTubeMiner.model.youtube.caption.Caption;
import aiss.YouTubeMiner.services.CaptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CaptionServiceTest {

    @Autowired
    CaptionService captionService;
    @Test
    void findCaptions(){
        CaptionService captionService = new CaptionService();
        String videoId = "gwUfIt2s0q0";
        List<Caption> captions = captionService.findCaptions(videoId);
        assertNotNull(captions, "La lista de captions no debe ser nula");
        assertFalse(captions.isEmpty(), "La lista de captions debe contener elementos");
        System.out.println(captions);
    }
}

