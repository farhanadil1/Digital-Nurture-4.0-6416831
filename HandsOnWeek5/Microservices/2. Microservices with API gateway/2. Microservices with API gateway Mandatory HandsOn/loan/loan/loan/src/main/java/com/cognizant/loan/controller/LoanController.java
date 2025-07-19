package com.cognizant.loan.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/")
    public String home() {
        return "Loan Service is running!";
    }

    @GetMapping("/loans/{id}")
    public Map<String, Object> getLoan(@PathVariable String id) {
        // Dummy JSON response
        return Map.of(
            "number", "H00987987972342",
            "type", "car",
            "loan", 400000,
            "emi", 3258,
            "tenure", 18
        );
    }
}
