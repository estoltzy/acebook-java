package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import antlr.StringUtils;

import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }
    // include logic in index that checks parameters (whether button has been clicked for reverse)

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        repository.save(post);
        return new RedirectView("/posts");
    }

    @GetMapping("/posts/reverse")
    public String reverse(Model model) {
        Iterable<Post> reversed_posts = repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        model.addAttribute("reversed_posts", reversed_posts);
        return "posts/reverse";
        
    }


}
