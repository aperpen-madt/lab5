package com.aperpen.exchangerates;

import androidx.annotation.NonNull;

public class Currency {
    private final String code;
    private final String name;

    public Currency(String currencyCode) {
        this(currencyCode, null);
    }

    public Currency(String currencyCode, String currencyName) {
        this.code = currencyCode;
        this.name = currencyName;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return code;
    }
}
