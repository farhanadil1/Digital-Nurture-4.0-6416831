package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLogger {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLogger.class);

    public static void main(String[] args) {
        String username = "adil";
        int attempts = 4;

        logger.info("User '{}' has logged in {} times today.", username, attempts);
    }
}
