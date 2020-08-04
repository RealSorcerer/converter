package com.ruban.service;

import com.ruban.model.ConversionRequest;

import java.sql.Date;
import java.util.List;

public interface IHistoryService {
    List<ConversionRequest> getByDate(Date date);
    void save(ConversionRequest conversionRequest);
}
