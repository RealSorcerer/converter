package com.ruban.repository;


import com.ruban.model.ConversionRequest;
import com.ruban.model.CurrencyRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public interface HistoryPagingAndSortingRepository extends PagingAndSortingRepository<ConversionRequest, Long> {
    List<ConversionRequest> getByDate(Date date);
}