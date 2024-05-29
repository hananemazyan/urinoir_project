package com.example.urinoirapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LiveDataController {

    @GetMapping("/live")
    public String getLiveDataPage() {
        return "Live";
    }
}