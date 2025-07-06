package com.library.aspect;

public class LoggingAspect {

    public void beforeAdvice() {
        System.out.println("LOG Before method execution");
    }

    public void afterAdvice() {
        System.out.println("LOG After method execution");
    }
}
