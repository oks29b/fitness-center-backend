package com.example.servingwebcontent.exceptionHandling;

public class NoSuchPostException extends RuntimeException{
    public NoSuchPostException(String message) {
        super(message);
    }
}
