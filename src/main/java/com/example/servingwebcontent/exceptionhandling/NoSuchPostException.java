package com.example.servingwebcontent.exceptionhandling;

public class NoSuchPostException extends RuntimeException{
    public NoSuchPostException(String message) {
        super(message);
    }
}
