package com.example.expenseTracker.controller;

import com.example.expenseTracker.dto.request.ItemSubcategoryRequest;
import com.example.expenseTracker.dto.response.ItemSubcategoryResponse;
import com.example.expenseTracker.exception.ItemSubcategoryInUseException;
import com.example.expenseTracker.exception.ItemSubcategoryNotFoundException;
import com.example.expenseTracker.service.ItemSubcategoryService;
import com.example.expenseTracker.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base.url}/item-subcategory")
@AllArgsConstructor
public class ItemSubcategoryController {
    private final ItemSubcategoryService itemSubcategoryService;
    private final ResponseService responseService;
    @PostMapping
    public ResponseEntity create(@RequestBody ItemSubcategoryRequest itemSubcategoryRequest){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.CREATED;
            return new ResponseEntity<>(itemSubcategoryService.create(itemSubcategoryRequest),httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
    @PutMapping
    public ResponseEntity update(@RequestBody ItemSubcategoryRequest itemSubcategoryRequest){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(itemSubcategoryService.update(itemSubcategoryRequest), httpStatus);
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
            itemSubcategoryService.delete(id);
            return new ResponseEntity<>(responseService.delete("item-subcategory", httpStatus), httpStatus);
        }catch (ItemSubcategoryNotFoundException | ItemSubcategoryInUseException e){
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
    @GetMapping
    public ResponseEntity get(@RequestParam(name = "name", required = false)String name,
                              @RequestParam(name = "categoryId", required = false)Long categoryId){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(itemSubcategoryService.get(name,categoryId), httpStatus);
        }
        catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
    @GetMapping("/get")
    public ResponseEntity getByName(@RequestParam(name = "name") String name){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;

            return new ResponseEntity<>(itemSubcategoryService.getByName(name), httpStatus);
        }catch (ItemSubcategoryNotFoundException e){
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
}
