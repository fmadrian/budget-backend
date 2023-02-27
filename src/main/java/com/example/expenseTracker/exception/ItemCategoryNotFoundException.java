package com.example.expenseTracker.exception;

public class ItemCategoryNotFoundException extends RuntimeException{
    public ItemCategoryNotFoundException(String id){
        super("Item category " + id + " not found.");
    }
}
