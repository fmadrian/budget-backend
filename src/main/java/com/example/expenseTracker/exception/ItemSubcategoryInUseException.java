package com.example.expenseTracker.exception;

import com.example.expenseTracker.model.ItemSubcategory;

public class ItemSubcategoryInUseException extends RuntimeException{
    public ItemSubcategoryInUseException(ItemSubcategory i){
        super("Item subcategory " + i.getName() + " is being used.");
    }
}
