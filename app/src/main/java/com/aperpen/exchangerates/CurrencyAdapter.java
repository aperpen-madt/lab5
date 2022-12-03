package com.aperpen.exchangerates;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    public CurrencyAdapter(Context context, List<Currency> currencies) {
        super(context, R.layout.list_entry_currency, currencies);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View currencyView = view;

        // If view is not recyclable create one
        if (currencyView == null) {
            currencyView = LayoutInflater.from(getContext()).inflate(R.layout.list_entry_currency, parent,
                    false);
        }

        Currency currentCurrency = getItem(position);
        TextView tvCurrency = currencyView.findViewById(R.id.tvCurrency);

        tvCurrency.setText(currentCurrency.toString());

        return currencyView;
    }
}
