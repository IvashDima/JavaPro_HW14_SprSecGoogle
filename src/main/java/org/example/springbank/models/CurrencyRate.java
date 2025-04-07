package org.example.springbank.models;

import org.example.springbank.enums.CurrencyType;

import javax.persistence.*;

@Entity
@Table(name = "CurrencyRates")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyType currency;

    @Column(nullable = false)
    private double rate;

    public CurrencyRate(){}

    public CurrencyRate(CurrencyType currency, double rate){
        this.rate = rate;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }
    public CurrencyType getCurrency() {
        return currency;
    }
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString(){
        return "CurrencyRate{id="+id+", " +
                "currency='"+currency+"', " +
                "rate="+rate+
                "}";
    }
}
