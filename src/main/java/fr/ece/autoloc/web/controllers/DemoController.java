package fr.ece.autoloc.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/public/ping")
    public String publicPing() {
        return "Public ping successful!";
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/admin/report")
    public String adminReport() {
        return "Confidential adming report.";
    }
}
