package com.ruban.service;

import com.ruban.model.ConversionRequest;
import com.ruban.repository.HistoryPagingAndSortingRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Primary
@Service
public class HistoryService implements IHistoryService {
    private final HistoryPagingAndSortingRepository repository;

    public HistoryService(HistoryPagingAndSortingRepository repository) {
        this.repository = repository;
    }

    public List<ConversionRequest> getByDate(Date date) {
        return repository.getByDate(date);
    }

    public void save(ConversionRequest conversionRequest) {
        repository.save(conversionRequest);
    }
}
