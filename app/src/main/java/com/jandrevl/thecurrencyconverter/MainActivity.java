package com.jandrevl.thecurrencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String baseCurrency;
    String currency;
    String apiKey = "ocxUnvinnggVvHc14YPxzanGV5OUJCne";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner baseCurrenciesSpinner = findViewById(R.id.baseCurrencySpinner);
        Spinner currenciesSpinner = findViewById(R.id.currencySpinner);

        ArrayAdapter<CharSequence> baseCurrenciesArrayAdapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.baseCurrencies,
                        android.R.layout.simple_spinner_item);
        baseCurrenciesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        baseCurrenciesSpinner.setAdapter(baseCurrenciesArrayAdapter);

        ArrayAdapter<CharSequence> currenciesArrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.currencies,
                android.R.layout.simple_spinner_item);
        currenciesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        currenciesSpinner.setAdapter(currenciesArrayAdapter);

        baseCurrenciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                baseCurrency = getResources().getStringArray(R.array.baseCurrencies)[i].substring(0,3);
                Toast.makeText(MainActivity.this, baseCurrency, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                baseCurrency = "EUR";

            }
        });

        currenciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currency = getResources().getStringArray(R.array.currencies)[i].substring(0,3);
                Toast.makeText(MainActivity.this, currency, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                currency = "EUR";
            }
        });

    }
    
    public void goToConversions(View view) {

        Log.i("Conversions Button", "Clicked");
        DownloadTask downloadTask = new DownloadTask();
        String


    }
}