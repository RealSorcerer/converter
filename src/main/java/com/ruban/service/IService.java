package com.ruban.service;

import com.ruban.model.CurrencyRate;

import java.sql.Date;
import java.util.List;

public interface IService<T> {
    List<T> findByValuteId(String id);
    List<T> findByDateAndValuteIds(Date date, String valuteId1, String valuteId2);
    void save(T t);
}
