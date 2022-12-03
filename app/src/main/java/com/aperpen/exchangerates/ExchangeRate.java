package com.aperpen.exchangerates;

import androidx.annotation.NonNull;

public class ExchangeRate {
    private final Currency baseCurrency;
    private final Currency currency;
    private final double value;

    public ExchangeRate(Currency baseCurrency, Currency currency, double value) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    @NonNull
    @Override
    public String toString() {
        return getCurrency() + " - " + getValue();
    }
}
