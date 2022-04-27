package com.makersacademy.acebook.controller;

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
        return "/account";
    }

    @PostMapping("/account")
    public RedirectView submitProfilePhoto(@ModelAttribute User user, String photoLocation) {
        user.setPhotoLocation(photoLocation);
        userRepository.save(user);
        return new RedirectView("/account");
    }
}


//     public RedirectView create(@ModelAttribute Post post, Principal principal) {
//         post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
//         post.setUser(getLoggedInUser(principal));
//         repository.save(post);
//         return new RedirectView("/posts");
   
// @GetMapping("/comments")
// public String index(Model model) {
//     Iterable<Comment> comments = commentRepository.findAll();
//     model.addAttribute("comment", new Comment());
//     model.addAttribute("comments", comments);
//     return "comments/index";
// }
