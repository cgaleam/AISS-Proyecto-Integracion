package aiss.videominer.controller;

import aiss.videominer.repository.CommentRepository;
import aiss.videominer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videominer/users")
public class UserController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;
}
