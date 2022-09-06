package com.jandrevl.thecurrencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyCalculationActivity extends AppCompatActivity {

    EditText baseCurrencyEditText;
    EditText currencyEditText;
    BigDecimal conversionRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_calculation);

        baseCurrencyEditText = findViewById(R.id.baseCurrencyEditText);
        currencyEditText = findViewById(R.id.secondaryCurrencyEditText);
        baseCurrencyEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                baseCurrencyEditText.setText(null);
                currencyEditText.setText(null);
                return false;
            }
        });
        currencyEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                baseCurrencyEditText.setText(null);
                currencyEditText.setText(null);
                return false;
            }
        });

        Intent intent = getIntent();

        String baseCurrency = intent.getStringExtra("baseCurrency");
        String currency = intent.getStringExtra("currency");
        TextView baseCurrencyTextView = findViewById(R.id.baseCurrencyTextView);
        TextView currencyTextView = findViewById(R.id.secondaryCurrencyTextView);
        baseCurrencyTextView.setText(baseCurrency);
        currencyTextView.setText(currency);

        conversionRate = new BigDecimal(intent.getStringExtra("conversionRate"));
        Log.i("Conversion Rate received", String.valueOf(conversionRate));

    }

    public void calculateConversion(View view) {
        if(baseCurrencyEditText.getText().toString().equals("") &&
                currencyEditText.getText().toString().equals("")) {
            // Consider changing the following Toast to a Dialog Box
            Toast.makeText(this,
                    "Please insert valid amount in one of the currencies",
                    Toast.LENGTH_SHORT).show();

        }

        if(!baseCurrencyEditText.getText().toString().equals("") && currencyEditText.getText().toString().equals("")) {
            Toast.makeText(this, "base to secondary", Toast.LENGTH_SHORT).show();
            BigDecimal baseCurrencyAmount = new BigDecimal(baseCurrencyEditText.getText().toString()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal currencyAmount = baseCurrencyAmount.multiply(conversionRate).setScale(2,RoundingMode.HALF_UP);
            currencyEditText.setText(currencyAmount.toString());
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(baseCurrencyEditText.getWindowToken(), 0);


        }

        if(baseCurrencyEditText.getText().toString().equals("") && !currencyEditText.getText().toString().equals("")) {
            Toast.makeText(this, "secondary to base", Toast.LENGTH_SHORT).show();
            BigDecimal currencyAmount = new BigDecimal(currencyEditText.getText().toString()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal baseCurrencyAmount = currencyAmount.divide(conversionRate, 2, RoundingMode.HALF_UP);
            baseCurrencyEditText.setText(baseCurrencyAmount.toString());
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currencyEditText.getWindowToken(), 0);

        }


    }



    public void backToMainActivity(View view) {
        finish();
    }
}