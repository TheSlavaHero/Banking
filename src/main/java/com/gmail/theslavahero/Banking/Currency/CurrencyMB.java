package com.gmail.theslavahero.Banking.Currency;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CurrencyMB {
    private Integer currencyCodeA;
    private Integer currencyCodeB;
    private long date;
    private Double rateBuy;
    private Double rateSell;

    public CurrencyMB(Integer currencyCodeA, Integer currencyCodeB, long date, Double rateBuy, Double rateSell) {
        this.currencyCodeA = currencyCodeA;
        this.currencyCodeB = currencyCodeB;
        this.date = date;
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
    }
    public static CurrencyMB[] fromJsonMB(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, CurrencyMB[].class);
    }
    public CurrencyMB() {
    }

    public Integer getCurrencyCodeA() {
        return currencyCodeA;
    }

    public void setCurrencyCodeA(Integer currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public Integer getCurrencyCodeB() {
        return currencyCodeB;
    }

    public void setCurrencyCodeB(Integer currencyCodeB) {
        this.currencyCodeB = currencyCodeB;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Double getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(Double rateBuy) {
        this.rateBuy = rateBuy;
    }

    public Double getRateSell() {
        return rateSell;
    }

    public void setRateSell(Double rateSell) {
        this.rateSell = rateSell;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", date=" + date +
                ", rateBuy=" + rateBuy +
                ", rateSell=" + rateSell +
                '}';
    }
}
