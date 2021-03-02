package com.gmail.theslavahero.Banking.Servlets;

import com.gmail.theslavahero.Banking.Currency.Currency;
import com.gmail.theslavahero.Banking.Currency.CurrencyDataPB;
import com.gmail.theslavahero.Banking.Currency.CurrencyMB;
import com.gmail.theslavahero.Banking.Currency.CurrencyPB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.gmail.theslavahero.Banking.Currency.Currency.toJson;
import static com.gmail.theslavahero.Banking.Currency.CurrencyDataPB.fromJsonPB;
import static com.gmail.theslavahero.Banking.Currency.CurrencyMB.fromJsonMB;
import static com.gmail.theslavahero.Banking.WebConnection.getJsonfromMB;
import static com.gmail.theslavahero.Banking.WebConnection.getJsonfromPB;

@WebServlet(name = "StartServlet", value = "/start")
public class StartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Currency[] currencies = new Currency[4];
        String jsonPB = getJsonfromPB();
        CurrencyDataPB currencyDataPB = fromJsonPB(jsonPB);
        CurrencyPB[] currencyPBS = currencyDataPB.getExchangeRate();
        for (CurrencyPB currencyPB : currencyPBS) {
            try {
                if (currencyPB.getCurrency().equals("USD")) {
                    currencies[0] = new Currency("Privatbank", "USD", currencyPB.getSaleRate(), currencyPB.getPurchaseRate());
                }
                if (currencyPB.getCurrency().equals("EUR")) {
                    currencies[1] = new Currency("Privatbank", "EUR", currencyPB.getSaleRate(), currencyPB.getPurchaseRate());
                }
            } catch (NullPointerException e) {
            }
        }
        String jsonMB = getJsonfromMB();
        CurrencyMB[] currencyMBS = fromJsonMB(jsonMB);
        for (CurrencyMB currencyMB : currencyMBS) {
            if (currencyMB.getCurrencyCodeA().equals(840) && currencyMB.getCurrencyCodeB().equals(980)) {
                currencies[2] = new Currency("Monobank", "USD", currencyMB.getRateBuy(), currencyMB.getRateSell());
            }
            if (currencyMB.getCurrencyCodeA().equals(978) && currencyMB.getCurrencyCodeB().equals(980)) {
                currencies[3] = new Currency("Monobank", "EUR", currencyMB.getRateBuy(), currencyMB.getRateSell());
            }
        }
        String currencyJson = toJson(currencies);
        PrintWriter out = response.getWriter();
        out.println(currencyJson);
    }
}
