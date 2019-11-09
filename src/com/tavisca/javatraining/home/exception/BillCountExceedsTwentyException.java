package com.tavisca.javatraining.home.exception;

public class BillCountExceedsTwentyException extends Exception {
    public BillCountExceedsTwentyException() {
        super("Can't withdraw more than 20 bills at at time");
    }
}
