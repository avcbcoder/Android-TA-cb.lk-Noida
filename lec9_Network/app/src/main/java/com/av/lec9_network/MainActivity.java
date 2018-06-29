package com.av.lec9_network;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv_show);
        et = findViewById(R.id.et);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyNetworkTask task = new MyNetworkTask();
                task.execute(et.getText().toString());
            }
        });
    }

    class MyNetworkTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            // Connect with network here
            String s = networkCall(strings[0]);
            return s;
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

    private String networkCall(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();// returns URL connection
            InputStream inputStream = connect.getInputStream();
            Scanner sc = new Scanner(inputStream);
            String s = "";

            // Method 1 => Not efficient
            /* StringBuilder sb = new StringBuilder("");
            if (sc.hasNext()) {
                sb.append(sc.next());
            }
            return sb.toString();
            */

            // Method 2 => Use Buffered reader

            // Method 3 => Efficient using delimiters
            sc.useDelimiter("\\A"); // read content in one go
            if (sc.hasNext())
                s = sc.next();
            return s;
        } catch (Exception e) {

        }
        return "OOPS";
    }
}
