package com.apirest.customer.exception;

public class NotLegalAgeException extends Exception{
    public NotLegalAgeException(String message) {
        super(message);
    }

    public NotLegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
