package com.javier.app_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/accounts")
public class AccountsController {

    //@PreAuthorize("hasAnyAuthority('VIEW_ACCOUNT', 'VIEW_CARDS')")
    @GetMapping
    public Map<String, String> Accounts() {
        // ... business logic of service
        return Map.of("message", "Accounts available");

    }
}
