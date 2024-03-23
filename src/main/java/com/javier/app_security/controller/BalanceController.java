package com.javier.app_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/balanec")
public class BalanceController {
    @GetMapping
    public Map<String, String> Balance() {
        // ... business logic of service
        return Map.of("message", "Balance available");
    }
}
