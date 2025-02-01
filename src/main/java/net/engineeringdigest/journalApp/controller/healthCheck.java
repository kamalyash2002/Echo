package net.engineeringdigest.journalApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {
    @GetMapping("/health-check")
    public String checking(){
        System.out.println("Health check");
        return "OK";
    }
}
