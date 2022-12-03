package com.aperpen.exchangerates.api;

import android.os.AsyncTask;

import com.aperpen.exchangerates.Currency;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public class CurrenciesLoader extends AsyncTask<String, Void, List<Currency>> {
    public CurrenciesLoader() {
        super();
    }

    @Override
    protected List<Currency> doInBackground(String... s) {
        Call<JsonObject> call = RetrofitClient.getInstance().getApi()
                .getCurrencies();
        try {
            List<Currency> currencies = new ArrayList<>();
            Response<JsonObject> response = call.execute();
            JsonObject jsonResponse = response.body();
            JsonObject jsonCurrencies = Objects.requireNonNull(jsonResponse).getAsJsonObject("symbols");
            for (String currencyCode : jsonCurrencies.keySet()) {
                Currency currency = new Currency(
                        currencyCode,
                        jsonCurrencies.get(currencyCode).getAsString()
                );
                currencies.add(currency);
            }

            return currencies;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
