import com.gmail.theslavahero.Banking.Currency.Currency;
import com.gmail.theslavahero.Banking.Currency.CurrencyDataPB;
import com.gmail.theslavahero.Banking.Currency.CurrencyMB;
import com.gmail.theslavahero.Banking.Currency.CurrencyPB;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static com.gmail.theslavahero.Banking.Currency.Currency.toJson;
import static com.gmail.theslavahero.Banking.Currency.CurrencyDataPB.fromJsonPB;
import static com.gmail.theslavahero.Banking.Currency.CurrencyMB.fromJsonMB;
import static com.gmail.theslavahero.Banking.WebConnection.getJsonfromMB;
import static com.gmail.theslavahero.Banking.WebConnection.getJsonfromPB;
import static org.junit.Assert.*;

public class StartServletTest {
    @Test
    public void testStart() throws IOException {
        Currency[] currencies = new Currency[4];
        String jsonPB = getJsonfromPB();
        CurrencyDataPB currencyDataPB = fromJsonPB(jsonPB);
        CurrencyPB[] currencyPBS = currencyDataPB.getExchangeRate();
        try {
            for (CurrencyPB currencyPB : currencyPBS) {
                if (currencyPB.getCurrency().equals("USD")) {
                    currencies[0] = new Currency("Privatbank", "USD", currencyPB.getSaleRate(), currencyPB.getPurchaseRate());
                }
                if (currencyPB.getCurrency().equals("EUR")) {
                    currencies[1] = new Currency("Privatbank", "EUR", currencyPB.getSaleRate(), currencyPB.getPurchaseRate());
                }
            }
        } catch (NullPointerException e) {}
        String jsonMB = getJsonfromMB();
        CurrencyMB[] currencyMBS = fromJsonMB(jsonMB);
        for (CurrencyMB currencyMB : currencyMBS) {
            if (currencyMB.getCurrencyCodeA().equals(840) && currencyMB.getCurrencyCodeB().equals(980)) {
                currencies[2] = new Currency("Monobank", "USD", currencyMB.getRateBuy(),currencyMB.getRateSell());
            }
            if (currencyMB.getCurrencyCodeA().equals(978) && currencyMB.getCurrencyCodeB().equals(980)) {
                currencies[3] = new Currency("Monobank", "EUR", currencyMB.getRateBuy(),currencyMB.getRateSell());
            }
        }
        String currencyJson = toJson(currencies);
        assertNotNull(currencyJson);
    }
}
