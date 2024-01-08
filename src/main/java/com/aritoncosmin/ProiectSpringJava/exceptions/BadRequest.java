package com.aritoncosmin.ProiectSpringJava.exceptions;

public class BadRequest extends RuntimeException{
    public BadRequest(String message){
        super(message);
    }
}
