package com.tavisca.javatraining.home.exception;

public class InvalidAmountException extends Exception {
    public InvalidAmountException() {
        super("Amount should be multiple of 100");
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}
