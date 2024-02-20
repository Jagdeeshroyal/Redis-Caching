package com.jagadeesh.redisexample.exception;

public class HumanNotFoundException extends RuntimeException {

    public HumanNotFoundException(String message) {
        super(message);
    }
}
