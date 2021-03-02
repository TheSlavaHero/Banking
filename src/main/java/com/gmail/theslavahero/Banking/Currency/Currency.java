package com.gmail.theslavahero.Banking.Currency;

import com.google.gson.Gson;

public class Currency {
    private String bank;
    private String currency;
    private double saleRate;
    private double purchaseRate;

    public Currency(String bank, String currency, double saleRate, double purchaseRate) {
        this.bank = bank;
        this.currency = currency;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }
    public static String toJson(Currency[] currency) {
        return new Gson().toJson(currency);
    }
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(double saleRate) {
        this.saleRate = saleRate;
    }

    public double getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "bank='" + bank + '\'' +
                ", currency='" + currency + '\'' +
                ", saleRate=" + saleRate +
                ", purchaseRate=" + purchaseRate +
                '}';
    }
}
