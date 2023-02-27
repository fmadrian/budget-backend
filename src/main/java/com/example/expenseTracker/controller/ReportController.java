package com.example.expenseTracker.controller;

import com.example.expenseTracker.dto.request.ReportRequest;
import com.example.expenseTracker.exception.NoReportsCreatedException;
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
    public ResponseEntity delete(@PathVariable(name = "id")String id){
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
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(name = "id")String id){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(reportService.getById(id), httpStatus);
        }catch (ReportNotFoundException e){
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }

    @GetMapping("/get-last")
    public ResponseEntity getLastCreated(){
        HttpStatus httpStatus = null;
        try {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(reportService.getLastCreated(), httpStatus);
        }catch (NoReportsCreatedException e){
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }

    /**
     *
     * @param name Name of the report.
     * @param since Date since we want to search reports.
     * @param until Date until we want to search reports.
     * @param page Page number to be returned.
     * @param items Amount of items to be returned in a page.
     * @return
     */
    @GetMapping("")
    public ResponseEntity search(@RequestParam(name="name")String name, @RequestParam(name = "since")String since,
                                 @RequestParam(name = "until")String until,
                                 @RequestParam(name = "page",required = false)Integer page,
                                 @RequestParam(name= "items",required = false) Integer items){
        HttpStatus httpStatus = null;
        try{
            httpStatus = HttpStatus.OK;
            return new ResponseEntity(reportService.search(name,since,until,page,items), httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }
    @GetMapping("/search-size")
    public ResponseEntity searchSize(@RequestParam(name="name")String name, @RequestParam(name = "since")String since,
                                     @RequestParam(name = "until")String until,
                                     @RequestParam(name= "items",required = false) Integer items){
        HttpStatus httpStatus = null;
        try{
            httpStatus = HttpStatus.OK;
            return new ResponseEntity(reportService.searchSize(name,since,until,items), httpStatus);
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(responseService.error(e,httpStatus),httpStatus);
        }
    }

}
