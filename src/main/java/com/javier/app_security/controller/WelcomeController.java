package com.javier.app_security.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    @GetMapping
    public Map<String, Object> welcome() {
        Map<String, Object> jsonObject = Map.of(
                "response", Map.of("message", "Welcome to the application"),
                "status", 200
        );



        //Extraigo
        Map<String,Object> extraccion = (Map<String,Object>)jsonObject.get("response");
        System.out.println(extraccion.get("message"));




        return jsonObject;

        //return Map.of("message", "Welcome to the application");

        //return Collections.singletonMap("message", "Welcome to the application"); otra forma
    }
}
