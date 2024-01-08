package com.aritoncosmin.ProiectSpringJava.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
