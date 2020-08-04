package com.ruban.controller;

import com.ruban.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class HistoryController {
    private HistoryService service;

    @Autowired
    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @GetMapping("/history")
    public List history(@RequestParam(value = "date", required = false) String date) {
        Date targetDate;
        if (date == null) {
            targetDate = new Date(System.currentTimeMillis());
        } else {
            targetDate = Date.valueOf(date);
        }

        return service.getByDate(targetDate);
    }


}
