package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.CommentRepository;

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

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    public static User getLoggedInUser(Principal principal, UserRepository userRepository) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        post.setUser(getLoggedInUser(principal, userRepository));
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
 @GetMapping("/posts/{id}/comments")
 public String comments(Model model, @PathVariable("id") int postID) {
    Iterable<Comment> comments = commentRepository.findAll();
     model.addAttribute("comment", new Comment());
     model.addAttribute("comments", comments);
     model.addAttribute("postID", postID);
     return "posts/comments";
 }

 @PostMapping("/posts/{id}/comments")
 public RedirectView create(@ModelAttribute Comment comment, @PathVariable("id") int postID) { 
   Optional<Post> post = repository.findById(Long.valueOf(postID));
    comment.setPost(post.get());
     commentRepository.save(comment);
     return new RedirectView("/posts/" + postID + "/comments");
 }

}
