package com.aperpen.exchangerates.api;

import android.os.AsyncTask;

import com.aperpen.exchangerates.Currency;
import com.aperpen.exchangerates.ExchangeRate;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public class ExchangeRatesLoader extends AsyncTask<Currency, Void, List<ExchangeRate>> {
    public ExchangeRatesLoader() {
        super();
    }

    @Override
    protected List<ExchangeRate> doInBackground(Currency... currency) {
        Currency baseCurrency = currency[0];
        Call<JsonObject> call = RetrofitClient.getInstance().getApi()
                .getExchangeRates(baseCurrency);

        try {
            List<ExchangeRate> rates = new ArrayList<>();
            Response<JsonObject> response = call.execute();
            JsonObject jsonResponse = response.body();
            JsonObject jsonRates = Objects.requireNonNull(jsonResponse).getAsJsonObject("rates");

            for (String currencyCode : jsonRates.keySet()) {
                Currency currentCurrency = new Currency(currencyCode);
                double value = jsonRates.get(currencyCode).getAsDouble();
                ExchangeRate exchangeRate = new ExchangeRate(baseCurrency, currentCurrency, value);
                rates.add(exchangeRate);
            }

            return rates;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
