package com.example.expenseTracker.exception;

public class ItemSubcategoryNotFoundException extends RuntimeException{
    public ItemSubcategoryNotFoundException(String id){
        super("Item subcategory " + id + " not found.");
    }
    public ItemSubcategoryNotFoundException(){
        super("Item subcategory not found.");
    }
}
