package com.ruban.service;

import com.ruban.model.CurrencyRate;
import com.ruban.repository.RatesPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class RatesService implements IRateService {
    private final RatesPagingAndSortingRepository repository;

    @Autowired
    public RatesService(RatesPagingAndSortingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CurrencyRate> findByValuteId(String id) {
        return repository.findByValuteId(id);
    }


    public List<CurrencyRate> findByDateAndValuteIds(Date date, String valuteId1, String valuteId2) throws IOException, SAXException, ParserConfigurationException {
        List<CurrencyRate> list = repository.findByDateAndValuteIds(date, valuteId1, valuteId2);
        if (list.isEmpty()) {
            loadRatesFromCbrf(date);
            list = repository.findByDateAndValuteIds(date, valuteId1, valuteId2);
        }
        return list;
    }

    @Override
    public List<CurrencyRate> getByDateAndValuteId(Date date, String valuteId) {
        return repository.getByDateAndValuteId(date, valuteId);
    }

    @Override
    public void save(CurrencyRate currencyRate) {
        Date date = new Date(System.currentTimeMillis());
        if (getByDateAndValuteId(currencyRate.getDate(), currencyRate.getValuteId()).isEmpty()) {
            repository.save(currencyRate);
        }
    }

    public List<CurrencyRate> loadRatesFromCbrf(Date dateReq) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Document document = factory.newDocumentBuilder().parse(
                new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dateFormat.format(dateReq)).openStream());

        String charsetName = document.getXmlEncoding();
        NodeList nodeList = document.getElementsByTagName("Valute");

        List<CurrencyRate> currencies = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                CurrencyRate rate = new CurrencyRate();
                rate.setValuteId(element.getAttribute("ID"));
                rate.setNumCode(element.getElementsByTagName("NumCode").item(0).getTextContent());
                rate.setCharCode(element.getElementsByTagName("CharCode").item(0).getTextContent());
                rate.setNominal(Integer.parseInt(element.getElementsByTagName("Nominal").item(0).getTextContent()));
                rate.setName(element.getElementsByTagName("Name").item(0).getTextContent());
                rate.setValue(Double.parseDouble(element.getElementsByTagName("Value").item(0).getTextContent()
                        .replace(',', '.')));
                rate.setDate(dateReq);
                currencies.add(rate);
                save(rate);
            }
        }
        return currencies;
    }
}
