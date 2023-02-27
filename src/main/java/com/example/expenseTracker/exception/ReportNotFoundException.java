package com.example.expenseTracker.exception;

public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(String id){
        super("Report " + id + " not found.");
    }
}
