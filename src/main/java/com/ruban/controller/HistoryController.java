package com.ruban.controller;

import com.ruban.model.ConversionRequest;
import com.ruban.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class HistoryController {
    @Autowired
    private HistoryService service;

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

    @PostMapping(value = "/history", consumes = "application/json", produces = "application/json")
    void newEmployee(@RequestBody ConversionRequest request) {
        service.save(request);
    }


}
