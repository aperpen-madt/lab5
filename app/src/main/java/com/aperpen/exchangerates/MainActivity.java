package com.aperpen.exchangerates;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aperpen.exchangerates.api.CurrenciesLoader;
import com.aperpen.exchangerates.api.ExchangeRatesLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner sCurrencies;
    ListView lvRates;
    ProgressBar erLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sCurrencies = findViewById(R.id.currenciesSelector);
        lvRates = findViewById(R.id.exchangeRates);
        erLoader = findViewById(R.id.erLoader);

        Button btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(v -> calculateRates());
    }

    protected void showErrorToast() {
        Toast.makeText(this, getString(R.string.api_error),
                Toast.LENGTH_LONG).show();
    }

    protected void calculateRates() {
        Currency baseCurrency = (Currency) sCurrencies.getSelectedItem();
        new ExchangeRatesLoader() {

        }.execute(baseCurrency);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new CurrenciesLoader() {
            @Override
            public void onPreExecute() {
                ProgressBar currencyLoader = findViewById(R.id.currencyLoader);
                currencyLoader.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPostExecute(List<Currency> result) {
                if (result == null) {
                    showErrorToast();
                } else {
                    CurrencyAdapter adapter = new CurrencyAdapter(getApplicationContext(), result);
                    sCurrencies.setAdapter(adapter);
                    ProgressBar currencyLoader = findViewById(R.id.currencyLoader);
                    currencyLoader.setVisibility(View.INVISIBLE);
                }
            }
        }.execute();
    }
}