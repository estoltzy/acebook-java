package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import antlr.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;

// latest version 26/04 14:37

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    private User getLoggedInUser(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        post.setUser(getLoggedInUser(principal));
        repository.save(post);
        return new RedirectView("/posts");
    }

    @GetMapping("/posts/reverse")
    public String reverse(Model model) {
        Iterable<Post> reversed_posts = repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        model.addAttribute("reversed_posts", reversed_posts);
        return "posts/reverse";
    }

    @PostMapping("/posts/incrementlikes")
    public RedirectView incrementLikes(@RequestParam Long postId) {
        Optional<Post> potentialPost = repository.findById(postId);
        if (potentialPost.isPresent()) {
            Post post = potentialPost.get();
            post.addLike();
            repository.save(post);
        }
        return new RedirectView("/posts");
    }
}
