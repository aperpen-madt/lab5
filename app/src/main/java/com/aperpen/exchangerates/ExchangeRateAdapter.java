package com.aperpen.exchangerates;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class ExchangeRateAdapter extends ArrayAdapter<ExchangeRate> {

    public ExchangeRateAdapter(Context context, List<ExchangeRate> erList) {
        super(context, R.layout.list_entry_er, erList);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View erView = view;

        // If view is not recyclable create one
        if (erView == null) {
            erView = LayoutInflater.from(getContext()).inflate(R.layout.list_entry_er, parent,
                    false);
        }

        ExchangeRate currentEr = getItem(position);
        TextView erCurrency = erView.findViewById(R.id.er_currency);
        TextView erValue = erView.findViewById(R.id.er_value);

        erCurrency.setText(currentEr.getCurrency().toString());
        erValue.setText(String.format(Locale.getDefault(), "%.6f", currentEr.getValue()));

        return erView;
    }
}
