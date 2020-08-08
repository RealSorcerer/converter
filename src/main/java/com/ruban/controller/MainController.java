package com.ruban.controller;

import com.ruban.model.ConversionRequest;
import com.ruban.model.CurrencyRate;
import com.ruban.model.LoginForm;
import com.ruban.service.IExchangeService;
import com.ruban.service.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {
    private IRateService rateService;
    private IExchangeService exchangeService;
    private List<CurrencyRate> currencies;

    @Autowired
    public MainController(IRateService rateService, IExchangeService exchangeService) throws IOException, SAXException, ParserConfigurationException {
        this.rateService = rateService;
        this.exchangeService = exchangeService;
        currencies = this.rateService.loadRatesFromCbrf(new Date(System.currentTimeMillis()));
    }

    @GetMapping("/")
    public String index(Model model) throws ParserConfigurationException, SAXException, IOException {
        model.addAttribute("currencies", currencies);
        model.addAttribute("conversion", new ConversionRequest());

        return "index";
    }

    @PostMapping("/")
    public String convert(@ModelAttribute ConversionRequest conversionRequest, Model model)
            throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException {

        ConversionRequest conversion = conversionRequest;
        HashMap<String,CurrencyRate> hashMap = rateService.findByDateAndValuteIds(new Date(System.currentTimeMillis()),
                conversionRequest.getSourceСurrency(), conversionRequest.getTargetCurrency());
        if (hashMap.size() == 0) {
            throw new IllegalArgumentException("There is no exchange rate for one of the currencies");
        }

        CurrencyRate sourceRate = hashMap.get(conversionRequest.getSourceСurrency());
        CurrencyRate targetRate = hashMap.get(conversionRequest.getTargetCurrency());
        double result = exchangeService.convert(sourceRate, targetRate, conversionRequest.getOriginalAmount());
        conversionRequest.setSourceCurrencyName(sourceRate.getCharCode() + "(" + sourceRate.getName() + ")");
        conversionRequest.setTargetCurrencyName(targetRate.getCharCode() + "(" + targetRate.getName() + ")");

        conversionRequest.setReceivedAmount(result);
        model.addAttribute("currencies", currencies);
        model.addAttribute("conversion", conversionRequest);
        exchangeService.save(conversionRequest);

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm login, Model model, HttpSession session) {
        if ("test".equals(login.getLogin()) && "test".equals(login.getPassword())) {
            session.setAttribute("login", login);
            return "redirect:/";
        }
        model.addAttribute("login", login);
        model.addAttribute("message", "Не удаётся войти.\n" +
                "Пожалуйста, проверьте правильность написания логина и пароля.");
        return "login";
    }

    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List history(@RequestParam(value = "date", required = false) String date) {
        Date targetDate;
        if (date == null) {
            targetDate = new Date(System.currentTimeMillis());
        } else {
            targetDate = Date.valueOf(date);
        }

        return exchangeService.getByDate(targetDate);
    }
}
