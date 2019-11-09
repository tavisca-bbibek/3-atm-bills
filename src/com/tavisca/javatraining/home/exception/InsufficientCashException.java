package com.tavisca.javatraining.home.exception;

public class InsufficientCashException extends Exception {

    public InsufficientCashException() {
        super("Insufficient balance");
    }

    public InsufficientCashException(String message) {
        super(message);
    }
}
