package com.jagadeesh.redisexample.exception;

public class HumanNotFoundByNameException extends HumanNotFoundException{


    public HumanNotFoundByNameException(String message) {
        super(message);
    }
}
