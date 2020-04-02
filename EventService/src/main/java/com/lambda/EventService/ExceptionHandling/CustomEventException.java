package com.lambda.EventService.ExceptionHandling;

public class CustomEventException extends Exception {
    public  CustomEventException(String errorMessage){
        super(errorMessage);
    }
}
