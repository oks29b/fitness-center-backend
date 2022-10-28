package com.example.servingwebcontent.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PostGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<PostIncorrectData> handleException(
            NoSuchPostException exception){
        PostIncorrectData data = new PostIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PostIncorrectData>handleException(Exception exception){
        PostIncorrectData data = new PostIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
