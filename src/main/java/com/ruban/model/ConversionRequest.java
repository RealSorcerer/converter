package com.ruban.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "history", schema = "public")
public class ConversionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "source_currency")
    private String sourceСurrency;
    @Column(name = "target_currency")
    private String targetCurrency;
    @Column(name = "original_amount")
    private double originalAmount;
    @Column(name = "received_amount")
    private double receivedAmount;
    private Date date;

    public ConversionRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSourceСurrency() {
        return sourceСurrency;
    }

    public void setSourceСurrency(String sourceСurrency) {
        this.sourceСurrency = sourceСurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
