package com.aperpen.exchangerates.api;

import com.aperpen.exchangerates.Currency;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ERDApi {
    String BASE_URL = "https://api.apilayer.com/exchangerates_data/";
    String API_KEY = "1AGazk5oVwKcDVrCK7uEYEMS2aJtsR67";

    @GET("symbols")
    Call<JsonObject> getCurrencies();

    @GET("latest")
    Call<JsonObject> getExchangeRates(@Query("base") Currency currency);

}
