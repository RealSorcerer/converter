package com.ruban.controller;

import com.ruban.service.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class MainController {
    private final IRateService service;

    @Autowired
    public MainController(IRateService service) {
        this.service = service;
    }


    @GetMapping("/")
    public String index() throws ParserConfigurationException, SAXException, IOException {
        List list1 = service.findByDateAndValuteIds(Date.valueOf("2020-09-05"), "R01090B", "R01100");

        return "index";
    }
}
