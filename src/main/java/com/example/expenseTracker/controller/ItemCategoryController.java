package com.example.expenseTracker.controller;

import com.example.expenseTracker.dto.base.ItemCategoryDto;
import com.example.expenseTracker.exception.ItemSubcategoryNotFoundException;
import com.example.expenseTracker.service.ItemCategoryService;
import com.example.expenseTracker.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base.url}/item-category")
@AllArgsConstructor
public class ItemCategoryController {
    private final ItemCategoryService itemCategoryService;
    private final ResponseService responseService;
    @PostMapping
    public ResponseEntity create(@RequestBody ItemCategoryDto itemCategoryDto){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.CREATED;
            return new ResponseEntity<>(itemCategoryService.create(itemCategoryDto), httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
    @PutMapping
    public ResponseEntity update(@RequestBody ItemCategoryDto itemCategoryDto){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(itemCategoryService.update(itemCategoryDto), httpStatus);
        }catch (ItemSubcategoryNotFoundException e){
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
        catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id")Long id){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;
            itemCategoryService.delete(id);
            return new ResponseEntity<>(responseService.delete("item-category", httpStatus), httpStatus);
        }catch (ItemSubcategoryNotFoundException e){
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
    @GetMapping
    public ResponseEntity get(@RequestParam(name = "name", required = false)String name){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(itemCategoryService.get(name), httpStatus);
        }
        catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }

    }
}
