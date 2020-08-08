package com.ruban.service;

import com.ruban.model.ConversionRequest;
import com.ruban.model.CurrencyRate;
import com.ruban.repository.ExchangePagingAndSortingRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Primary
@Service
public class ExchangeService implements IExchangeService {
    private final ExchangePagingAndSortingRepository repository;

    public ExchangeService(ExchangePagingAndSortingRepository repository) {
        this.repository = repository;
    }

    public List<ConversionRequest> getByDate(Date date) {
        return repository.getByDate(date);
    }

    public void save(ConversionRequest conversionRequest) {
        repository.save(conversionRequest);
    }

    public double convert(CurrencyRate sourceRate, CurrencyRate targetRate, double sourceAmount) {
        return (sourceRate.getValue() * targetRate.getNominal()) /
                (targetRate.getValue() * sourceRate.getNominal()) * sourceAmount;
    }
}
