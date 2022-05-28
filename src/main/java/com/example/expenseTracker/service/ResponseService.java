package com.example.expenseTracker.service;

import com.example.expenseTracker.dto.response.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class ResponseService {
    public GenericResponse error(Exception e, HttpStatus httpStatus){
        e.printStackTrace();
        return new GenericResponse(e.getMessage(),httpStatus.value(), e.getClass().getSimpleName());
    }
    /**
     *
     * @param name Deleted object name.
     * @param httpStatus
     * @return
     */
    public GenericResponse delete(String name, HttpStatus httpStatus){
        return new GenericResponse("Deleted", httpStatus.value(),name.toUpperCase(Locale.ROOT).concat(" deleted."));
    }
}
