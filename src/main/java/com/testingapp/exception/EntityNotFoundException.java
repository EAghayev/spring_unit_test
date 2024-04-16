package com.testingapp.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String msg){
        super(msg);
    }

    public EntityNotFoundException(){

    }
}
