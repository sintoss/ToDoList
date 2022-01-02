package com.ehei.gi4.amara.younes.todolist.controller;

import com.ehei.gi4.amara.younes.todolist.model.User;
import com.ehei.gi4.amara.younes.todolist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;
    private HttpSession session;
    public UserController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("registerRequest", new User());
        return "signup";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginRequest", new User());
        return "login";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute User userModel) {
        System.out.println("register request" + userModel);
        User registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(), userModel.getEmail());
        return registeredUser == null ? "redirect:/signup?error" : "redirect:/signup?success";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User userModel) {
        System.out.println("login request" + userModel);
        User authenticatedUser = userService.authenticate(userModel.getLogin(), userModel.getPassword());
        if (authenticatedUser != null) {
            session.setAttribute("user", authenticatedUser);
            return "redirect:/app";
        } else
            return "redirect:/login?error";
    }


}
