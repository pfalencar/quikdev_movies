package com.quikdev.exception;

public class ExistingEmailException extends RuntimeException{

    public ExistingEmailException(String message) {
        super(message);
    }

    public ExistingEmailException() {
    }
}
