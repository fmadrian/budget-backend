package com.example.expenseTracker.service;

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

    @Transactional
    public ReportResponse create(ReportRequest reportRequest){
        // Map the report.
        Report report = reportRepository.save(reportMapper.mapToEntity(reportRequest));
        // Save items before saving the report.
        itemRepository.saveAll(report.getItems());
        // Save the report.
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
    public void delete(Long id){
        // Search report.
        Report report = reportRepository.findById(id).orElseThrow(()-> new ReportNotFoundException(id));
        // Delete its items before deleting the report.
        itemRepository.deleteAll(report.getItems());
        // Delete the report.
        reportRepository.delete(report);
    }
    @Transactional(readOnly = true)
    public List<ReportResponse> get(String name, String since, String until){
        // Getting the dates.
        Instant isince = null, iuntil = null;
        if(since != null){
            isince = Instant.parse(since);
        }
        if(until != null){
            iuntil = Instant.parse(until);
        }

        // Map the results and return them.
        return reportRepository.findByNameContainsIgnoreCaseAndDateBetween(name,isince,iuntil).stream()
                .map(reportMapper :: mapToDto)
                .collect(Collectors.toList());

    }

    public ReportResponse getById(Long id) {
        return reportMapper.mapToDto(
                reportRepository.findById(id)
                        .orElseThrow(()-> new ReportNotFoundException(id))
        );
    }

    public ReportResponse getLastCreated() {
        return reportMapper.mapToDto(
                reportRepository.findTopByOrderByIdDesc().orElseThrow(()-> new NoReportsCreatedException()));
    }
}
