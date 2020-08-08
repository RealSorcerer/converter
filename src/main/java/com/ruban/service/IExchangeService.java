package com.ruban.service;

import com.ruban.model.ConversionRequest;
import com.ruban.model.CurrencyRate;

import java.sql.Date;
import java.util.List;

public interface IExchangeService {
    List<ConversionRequest> getByDate(Date date);
    void save(ConversionRequest conversionRequest);
    double convert(CurrencyRate sourceRate, CurrencyRate targetRate, double sourseAmount);
}
