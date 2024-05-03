package com.example.urinoirapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdminController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/log")
    public String login() {
        return "login";
    }
}
