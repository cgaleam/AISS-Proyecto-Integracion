package aiss.videominer.controller;

import aiss.videominer.model.Channel;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CommentRepository;
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
@RequestMapping("/videominer/comments")
public class CommentController {


    @Autowired
    VideoRepository videoRepository;
    @Autowired
    CommentRepository commentRepository;

    // GET http://localhost:8080/videominer/comments
    public List<Comment> getAllCommentByVideoId(@PathVariable(value = "videoId") long videoId) {
        Optional<Video> video = videoRepository.findById(String.valueOf(videoId));
        List<Comment> comments = new ArrayList<>(video.get().getComments());
        return comments;
    }

    // GET http://localhost:8080/videominer/comments/{id}
    @GetMapping("/{id}")
    public Comment findOne(@PathVariable long id) {
        Optional<Comment> comment = commentRepository.findById(String.valueOf(id));
        return comment.get();
    }
}
