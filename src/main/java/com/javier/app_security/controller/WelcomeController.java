package com.javier.app_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    @GetMapping
    public Map<String, String> welcome() {

        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the application");
        response.put("status", "success");
        response.put("version", "1.0");
        return response;

        //return Map.of("message", "Welcome to the application");

        //return Collections.singletonMap("message", "Welcome to the application"); otra forma
    }
}
