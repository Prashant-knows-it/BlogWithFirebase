package com.example.LoginPlusSecurity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.LoginPlusSecurity.model.User;
import com.example.LoginPlusSecurity.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public String getUserDetails(@PathVariable String username, Model model) {
        Optional<User> user = userService.getUserByName(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-details";
        }
        else {
            model.addAttribute("error", "User not found");
            return "error";
        }
    }
//    @GetMapping("")
//    public String userProfile() {
//    	
//    }
}
