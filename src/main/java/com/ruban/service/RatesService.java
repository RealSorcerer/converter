package com.ruban.service;

import com.ruban.model.CurrencyRate;
import com.ruban.repository.RatesPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Primary
@Service
public class RatesService implements IService<CurrencyRate> {
    private final RatesPagingAndSortingRepository repository;

    @Autowired
    public RatesService(RatesPagingAndSortingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CurrencyRate> findByValuteId(String id) {
        return repository.findByValuteId(id);
    }

    public List<CurrencyRate> findByDateAndValuteIds(Date date, String valuteId1, String valuteId2) {
        return repository.findByDateAndValuteIds(date, valuteId1, valuteId2);
    }

    @Override
    public void save(CurrencyRate currencyRate) {
        repository.save(currencyRate);
    }
}
