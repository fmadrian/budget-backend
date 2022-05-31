package com.example.expenseTracker.exception;

public class NoReportsCreatedException extends RuntimeException{
    public NoReportsCreatedException(){
        super("No reports have been created.");
    }
}
