package com.ruban.controller;

import com.ruban.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.util.List;

@Controller
public class MainController {
    private final IService service;

    @Autowired
    public MainController(IService service) {
        this.service = service;
    }


    @GetMapping("/")
    public String index() {
//        CurrencyRate currencyRate = new CurrencyRate();
//        currencyRate.setValuteId("R01020A");
//        currencyRate.setNumCode("944");
//        currencyRate.setCharCode("AZN");
//        currencyRate.setNominal(1);
//        currencyRate.setName("Азербайджанский манат");
//        currencyRate.setValue(43.1905);
//        service.save(currencyRate);
        List list = service.findByValuteId("R01010");
        System.out.println(list);

        List list1 = service.findByDateAndValuteIds(Date.valueOf("2020-08-04"), "R01020A", "R01010");
        return "index";
    }
}
