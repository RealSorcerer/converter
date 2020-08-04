package com.ruban.repository;


import com.ruban.model.CurrencyRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public interface RatesPagingAndSortingRepository extends PagingAndSortingRepository<CurrencyRate, Long> {

    @Query(value = "SELECT * FROM CURRENCY_RATES r WHERE r.valute_id = :valute_id", nativeQuery = true)
    List<CurrencyRate> findByValuteId(@Param("valute_id") String id);

    @Query(value = "SELECT * FROM CURRENCY_RATES r WHERE r.date = ?1 and (r.valute_id = ?2 or r.valute_id = ?3)", nativeQuery = true)
    List<CurrencyRate> findByDateAndValuteIds(Date date, String valuteId1, String valuteId2);

    List<CurrencyRate> getByDateAndValuteId(Date date, String valuteId);

}