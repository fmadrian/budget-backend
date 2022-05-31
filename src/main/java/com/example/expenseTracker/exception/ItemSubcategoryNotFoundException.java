package com.example.expenseTracker.exception;

public class ItemSubcategoryNotFoundException extends RuntimeException{
    public ItemSubcategoryNotFoundException(Long id){
        super("Item subcategory " + id + " not found.");
    }
    public ItemSubcategoryNotFoundException(){
        super("Item subcategory not found.");
    }
}
