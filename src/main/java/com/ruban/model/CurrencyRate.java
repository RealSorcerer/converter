package com.ruban.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "currency_rates", schema = "public")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "valute_id")
    private String valuteId;
    @Column(name = "num_code")
    private String numCode;
    @Column(name = "char_code")
    private String charCode;
    private int nominal;
    private String name;
    private double value;
    private Date date;

    public CurrencyRate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValuteId() {
        return valuteId;
    }

    public void setValuteId(String valuteId) {
        this.valuteId = valuteId;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return (int) id * valuteId.hashCode() * charCode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        CurrencyRate rhs = (CurrencyRate) obj;
        if (id != rhs.id) { return false;}
        if (!valuteId.equals(rhs.valuteId)) {
            return false;
        }
        if (!numCode.equals(rhs.numCode)) {
            return false;
        }
        if (charCode != rhs.charCode) {
            return false;
        }
        if (nominal != rhs.nominal) {
            return false;
        }
        if (!name.equals(rhs.name)) {
            return false;
        }
        if (value != rhs.value) {
            return false;
        }
        if (!date.equals(rhs.date)) {
            return false;
        }
        return true;
    }
}
