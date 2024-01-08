package com.aritoncosmin.ProiectSpringJava.exceptions;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message){
        super(message);
    }
}
