package com.jagadeesh.redisexample.exception;

public class HumanNotFoundByIdException extends HumanNotFoundException{

    public HumanNotFoundByIdException(String message) {
        super(message);
    }
}
