package com.jandrevl.thecurrencyconverter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {
        String result;
        URL url;
        HttpURLConnection httpURLConnection;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while(data != -1) {
                char current = (char)data;
                stringBuilder.append(current);
                data = reader.read();
            }
            result = stringBuilder.toString();
            return result;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
