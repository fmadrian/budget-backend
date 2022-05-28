package com.example.expenseTracker.controller;

import com.example.expenseTracker.dto.request.ReportRequest;
import com.example.expenseTracker.exception.ReportNotFoundException;
import com.example.expenseTracker.service.ReportService;
import com.example.expenseTracker.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base.url}/report")
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final ResponseService responseService;
    @PostMapping
    public ResponseEntity create(@RequestBody ReportRequest reportRequest){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.CREATED;
            return new ResponseEntity<>(reportService.create(reportRequest), httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
    @PutMapping
    public ResponseEntity update(@RequestBody ReportRequest reportRequest){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(reportService.update(reportRequest), httpStatus);
        }catch (ReportNotFoundException e){
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
            reportService.delete(id);
            return new ResponseEntity<>(responseService.delete("report", httpStatus), httpStatus);
        }catch (ReportNotFoundException e){
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
    @GetMapping
    public ResponseEntity get(@RequestParam(name = "name") String name,
                              @RequestParam(name = "since") String since,
                              @RequestParam(name = "until") String until){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(reportService.get(name,since,until), httpStatus);
        }
        catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
}
