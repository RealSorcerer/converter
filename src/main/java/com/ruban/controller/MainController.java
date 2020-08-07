package com.ruban.controller;

import com.ruban.model.ConversionRequest;
import com.ruban.model.CurrencyRate;
import com.ruban.model.LoginForm;
import com.ruban.service.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class MainController {
    private IRateService service;
    private List<CurrencyRate> currencies;

    @Autowired
    public MainController(IRateService service) throws IOException, SAXException, ParserConfigurationException {
        this.service = service;
        currencies = this.service.loadRatesFromCbrf(new Date(System.currentTimeMillis()));
    }

    @GetMapping("/")
    public String index(Model model) throws ParserConfigurationException, SAXException, IOException {
//        List list1 = service.findByDateAndValuteIds(Date.valueOf("2020-09-05"), "R01090B", "R01100");
        model.addAttribute("currencies", currencies);
        model.addAttribute("conversion", new ConversionRequest());

        return "index";
    }

    @PostMapping("/")
    public String convert(@ModelAttribute ConversionRequest request, Model model) {
        ConversionRequest conversion = new ConversionRequest();
        conversion.setSourceСurrency("R01020A");
        conversion.setTargetCurrency("R01090B");
        conversion.setOriginalAmount(123.63);
        conversion.setReceivedAmount(234.89);
        model.addAttribute("currencies", currencies);
        model.addAttribute("conversion", conversion);

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginForm());
        return "login";
    }
//
    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm login) {
        if ("test".equals(login.getLogin()) && "test".equals(login.getPassword()))
            return "redirect:/";
        return "redirect:/login";
    }
}
