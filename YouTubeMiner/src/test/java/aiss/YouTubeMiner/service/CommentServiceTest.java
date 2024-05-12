package aiss.YouTubeMiner.service;

import aiss.YouTubeMiner.model.youtube.comment.Comment;
import aiss.YouTubeMiner.services.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Test
    void findComments(){
        String videoId = "YDTANv-7k7g";
        List<Comment> comments = commentService.findComments(videoId, null);
        assertNotNull(comments, "La lista de comentarios no debe ser nula");
        assertFalse(comments.isEmpty(), "La lista de comentarios no debe estar vacía");
    }

}
