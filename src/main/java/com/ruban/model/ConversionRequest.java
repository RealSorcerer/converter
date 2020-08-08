package com.ruban.model;

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
    @Column(name = "source_currency_name")
    private String sourceCurrencyName;
    @Column(name = "target_currency")
    private String targetCurrency;
    @Column(name = "target_currency_name")
    private String targetCurrencyName;
    @Column(name = "original_amount")
    private double originalAmount;
    @Column(name = "received_amount")
    private double receivedAmount;
    @Column(name = "date", insertable = false)
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

    public String getSourceCurrencyName() {
        return sourceCurrencyName;
    }

    public void setSourceCurrencyName(String sourceCurrencyName) {
        this.sourceCurrencyName = sourceCurrencyName;
    }

    public String getTargetCurrencyName() {
        return targetCurrencyName;
    }

    public void setTargetCurrencyName(String targetCurrencyName) {
        this.targetCurrencyName = targetCurrencyName;
    }

    @Override
    public int hashCode() {
        return (int) id * sourceСurrency.hashCode() * targetCurrency.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ConversionRequest rhs = (ConversionRequest) obj;
        if (id != rhs.id) { return false;}
        if (!sourceСurrency.equals(rhs.sourceСurrency)) {
            return false;
        }
        if (!targetCurrency.equals(rhs.targetCurrency)) {
            return false;
        }
        if (originalAmount != rhs.originalAmount) {
            return false;
        }
        if (receivedAmount != rhs.receivedAmount) {
            return false;
        }
        if (!date.equals(rhs.date)) {
            return false;
        }
        return true;
    }
}
