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
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comments")
    public String index(Model model) {
        Iterable<Comment> comments = commentRepository.findAll();
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", comments);
        return "comments/index";
    }

    @PostMapping("/comments")
    public RedirectView create(@ModelAttribute Comment comment) {
        commentRepository.save(comment);
        return new RedirectView("/comments");
    }
    
}
