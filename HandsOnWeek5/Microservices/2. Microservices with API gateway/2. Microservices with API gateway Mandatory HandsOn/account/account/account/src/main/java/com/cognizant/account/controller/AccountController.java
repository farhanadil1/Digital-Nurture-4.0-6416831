package com.cognizant.account.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/")
    public String home() {
        return "Account Service is running!";
    }

    @GetMapping("/accounts/{id}")
    public Map<String, Object> getAccount(@PathVariable String id) {
        // Dummy JSON response
        return Map.of(
            "number", "00987987973432",
            "type", "savings",
            "balance", 234343
        );
    }
}