package aiss.VimeoMiner.service;

import aiss.VimeoMiner.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ServiceTest {

    @Autowired
    VideoService videoService;
    @Autowired
    ChannelService channelService;
    @Autowired
    CommentService commentService;
    @Autowired
    TextTrackService textTrackService;
    @Autowired
    UserService userService;


    @Test
    void testGetVideo() {
        VideoVM video = videoService.getVideo("868805980");
        assertNotNull(video, "El video obtenido es nulo");
        System.out.println(video);
    }


    @Test
    void testGetAllVideosOfChannel() {
        List<VideoVM> videos = videoService.getAllVideosOfChannel("music");
        assertFalse(videos.isEmpty(), "No se han encontrado videos en el canal");
        for (VideoVM video : videos) {
            assertNotNull(video, "El video obtenido es nulo");
            System.out.println(video);
        }
    }

    @Test
    void testGetUser() {
        UserVM user = userService.getUser("2881815");
        assertNotNull(user, "No se ha encontrado ningún usuario");
        System.out.println(user);
    }


    @Test
    void testGetTextTrack() {
        TextTrackVM textTrack = textTrackService.getTextTrack("868805980", "110823562");
        assertNotNull(textTrack, "No se ha encontrado ninguna pista de texto");
        System.out.println(textTrack);
    }

    @Test
    void testGetAllTextTracksOfVideo() {
        List<TextTrackVM> textTracks = textTrackService.getAllTextTracksOfVideo("868805980");
        assertFalse(textTracks.isEmpty(), "No se han encontrado pistas de texto para el video");
        for (TextTrackVM textTrack : textTracks) {
            assertNotNull(textTrack, "La pista de texto obtenida es nula");
            System.out.println(textTrack);
        }
    }

    @Test
    void testGetComment() {
        CommentVM comment = commentService.getComment("868805980", "20473657");
        assertNotNull(comment, "No se ha encontrado ningún comentario");
        System.out.println(comment);
    }

    @Test
    void testGetAllCommentsOfVideo() {
        List<CommentVM> comments = commentService.getAllCommentsOfVideo("868805980");
        assertFalse(comments.isEmpty(), "No se han encontrado comentarios para el video");
        for (CommentVM comment : comments) {
            assertNotNull(comment, "El comentario obtenido es nulo");
            System.out.println(comment);
        }
    }

    @Test
    void testGetChannel() {
        ChannelVM channel = channelService.getChannel("music");
        assertNotNull(channel, "No se ha encontrado ningún canal");
        System.out.println(channel);
    }
}