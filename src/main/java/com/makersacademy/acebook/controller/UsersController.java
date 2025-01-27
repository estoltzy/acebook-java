package com.makersacademy.acebook.controller;

import java.security.Principal;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {
        userRepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/users");
    }

    @GetMapping("/users")
    public RedirectView login() {
        return new RedirectView("/login");
    }
    
    @GetMapping("/users/add-friends")
    public String befriendUser(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/users/add-friends";
    }

    @GetMapping("/users/message-friend")
    public String message(){
           return "/users/message-friend";
    }

    @GetMapping("/account")
    public String addProfilePhoto(Model model) {
        model.addAttribute("user", new User());
        return "/account";
    }

    @PostMapping("/account")
    public RedirectView addProfilePhoto(@ModelAttribute User user, Principal principal) {
        User loggedInUser = PostsController.getLoggedInUser(principal, userRepository);
        loggedInUser.setPhotoLocation(user.getPhotoLocation());
        userRepository.save(loggedInUser);
        return new RedirectView("/account");
    }
}

