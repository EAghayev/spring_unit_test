package com.testingapp.exception;

public class EntityDublicateException extends RuntimeException{
    public EntityDublicateException(String msg){
        super(msg);
    }

    public EntityDublicateException(){

    }
}
