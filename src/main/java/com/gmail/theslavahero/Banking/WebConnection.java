package com.gmail.theslavahero.Banking;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WebConnection {
    private static final String urlPrivat = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
    private static final String urlMono = "https://api.monobank.ua/bank/currency";

    public static String getJsonfromPB() throws IOException {//returns Privatbank fresh currency
        String json = "";
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateStr = simpleDateFormat.format(date);
        Scanner sc = new Scanner(new URL(urlPrivat + dateStr).openStream());
        if (sc.hasNextLine()) {
            json = json + sc.nextLine();
        }
        return json;
    }

    public static String getJsonfromMB() throws IOException {//returns Monobank fresh currency
        String json = "";
        Scanner sc = new Scanner(new URL(urlMono).openStream());
        if (sc.hasNextLine()) {
            json = json + sc.nextLine();
        }
        return json;
    }
}
