package com.lambda.NotificationService.ExceptionHandling;

public class CustomException extends Exception {
    public  CustomException(String errorMessage){
        super(errorMessage);
    }
}
