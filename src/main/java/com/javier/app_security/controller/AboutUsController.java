package com.javier.app_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/about_us")
public class AboutUsController {
    @GetMapping
    public Map<String, String> aboutUs() {
        // ... business logic of service
        return Map.of("message", "About us available");

    }
}
