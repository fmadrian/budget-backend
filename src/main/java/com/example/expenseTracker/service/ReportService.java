package com.example.expenseTracker.service;

import com.example.expenseTracker.config.ConfigProperties;
import com.example.expenseTracker.dto.base.PaginationDto;
import com.example.expenseTracker.dto.request.ReportRequest;
import com.example.expenseTracker.dto.response.ReportResponse;
import com.example.expenseTracker.exception.NoReportsCreatedException;
import com.example.expenseTracker.exception.ReportNotFoundException;
import com.example.expenseTracker.mapper.ReportMapper;
import com.example.expenseTracker.model.Item;
import com.example.expenseTracker.model.Report;
import com.example.expenseTracker.repository.ItemRepository;
import com.example.expenseTracker.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportMapper reportMapper;
    private final ReportRepository reportRepository;
    private final ItemRepository itemRepository;
    private final ConfigProperties configProperties;

    @Transactional
    public ReportResponse create(ReportRequest reportRequest){
        // Map the report.
        Report report = reportMapper.mapToEntity(reportRequest);
        // Save items before saving the report.
        itemRepository.saveAll(report.getItems());
        // Save the report.
        reportRepository.save(report);
        return reportMapper.mapToDto(report);
    }
    @Transactional
    public ReportResponse update(ReportRequest reportRequest) {
        Report oldReport = reportRepository.findById(reportRequest.getId())
                .orElseThrow(()-> new ReportNotFoundException(reportRequest.getId()));
        Report updatedReport = reportMapper.mapToEntity(reportRequest);
        // Remove the items that don't show up in the updated report (if there's any).
        List<Item> deletedItems = oldReport.getItems()
                .stream().filter((item) -> !updatedReport.getItems().contains(item))
                .collect(Collectors.toList());
        itemRepository.deleteAll(deletedItems);
        // Save the updated / created items.
        itemRepository.saveAll(updatedReport.getItems());
        // Save the report and return it.
        return reportMapper.mapToDto(reportRepository.save(updatedReport));
    }
    @Transactional
    public void delete(String id){
        // Search report.
        Report report = reportRepository.findById(id).orElseThrow(()-> new ReportNotFoundException(id));
        // Delete its items before deleting the report.
        itemRepository.deleteAll(report.getItems());
        // Delete the report.
        reportRepository.delete(report);
    }
    @Transactional(readOnly = true)
    public List<ReportResponse> search(String name, String since, String until){
        // Getting the dates.
        Instant isince = null, iuntil = null;
        if(since != null){
            isince = Instant.parse(since);
        }
        if(until != null){
            iuntil = Instant.parse(until);
        }

        // Map the results and return them.
        return reportRepository.findByNameContainsIgnoreCaseAndDateBetweenOrderByDateAsc(name,isince,iuntil).stream()
                .map(reportMapper :: mapToDto)
                .collect(Collectors.toList());

    }
    @Transactional(readOnly = true)
    public ReportResponse getById(String id) {
        return reportMapper.mapToDto(
                reportRepository.findById(id)
                        .orElseThrow(()-> new ReportNotFoundException(id))
        );
    }
    @Transactional(readOnly = true)
    public ReportResponse getLastCreated() {
        return reportMapper.mapToDto(
                reportRepository.findTopByOrderByIdDesc().orElseThrow(()-> new NoReportsCreatedException()));
    }
    @Transactional(readOnly = true)
    public List<ReportResponse> search(String name, String since, String until, Integer page, Integer items) {
        // Parse the date range
        Instant iSince = null, iUntil = null;
        if(since != null)
            iSince = Instant.parse(since);
        if(until != null)
            iUntil = Instant.parse(until);
        Pageable pageRequest = null;
        // If we don't have a page number and the amount of items required, we return all the items.
        if(page != null && items != null) {
            // Build a pageable object (to get the pagination).
            pageRequest = PageRequest.of(page, items);
            return reportRepository.findByNameContainsIgnoreCaseAndDateBetweenOrderByDateAsc(name, iSince, iUntil, pageRequest)
                    .stream()
                    .map(reportMapper::mapToDto)
                    .collect(Collectors.toList());
        }else{
            return reportRepository.findByNameContainsIgnoreCaseAndDateBetweenOrderByDateAsc(name,iSince,iUntil).stream()
                    .map(reportMapper :: mapToDto)
                    .collect(Collectors.toList());
        }
    }
    @Transactional(readOnly = true)
    public PaginationDto searchSize(String name, String since, String until, Integer items) {
        // Returns the size of a query.
        List<ReportResponse> reports = null;
        Integer pages = 1, total = 0;
        if(items != null) {
            reports = this.search(name, since, until);
        }else{
            reports = this.search(name, since, until,1,items);
        }
        total = reports.size();
        // If we pass items per page amount, we calculate the amount of pages. If we don't pass it, pages equals to 1.

        if(items != null)
            pages = Integer.parseInt(String.valueOf(Math.round(Math.ceil(Double.valueOf(total/items)))));

        return new PaginationDto(total, pages);
    }
}
