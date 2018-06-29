package com.av.lec9_json_to_java;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tvResult);
        AsyncJSONTask task = new AsyncJSONTask();
        task.execute("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=6c98b7e685cc4b7e93076ac09c9d4639");
    }

    class AsyncJSONTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream stream = httpURLConnection.getInputStream();
                Scanner sc = new Scanner(stream);
                if (sc.hasNext())
                    return sc.next();
            } catch (Exception e) {

            }
            return "";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
