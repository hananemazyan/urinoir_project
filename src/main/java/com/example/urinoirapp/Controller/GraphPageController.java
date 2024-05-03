package com.example.urinoirapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphPageController {

    @GetMapping("/graph-page")
    public String graphPage() {
        return "graph"; // This should match the name of your HTML file without the extension
    }
}