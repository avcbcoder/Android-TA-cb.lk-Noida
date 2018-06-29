package com.av.lec9_threads;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tv;
    ProgressBar pb;
    MyAsyncTask asyncTask;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.ma_btn);
        tv = findViewById(R.id.ma_tv);
        pb = findViewById(R.id.ma_pb);
        pb.setVisibility(View.GONE);
        asyncTask = new MyAsyncTask();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startCount(100000);
                if (asyncTask.getStatus() == AsyncTask.Status.RUNNING)
                    asyncTask.cancel(true);
                asyncTask.execute(1000000);
            }
        });
    }

    private void startCount(final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < i; x++)
                    Log.e(TAG, "startCount: " + x);
                //tv.setText("Completed");// This line will not run since another thread can not alter UI thread
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Completed");
                    }
                });
            }
        }).start();
    }

    // Input , Progress, Result
    // keep those params void which you dont want since void is wrapper around null
    // Restriction : Only 5-6 Async Task can run at a time
    class MyAsyncTask extends AsyncTask<Integer, String, Boolean> {

        @Override // Background thread
        protected Boolean doInBackground(Integer... integers) {
            Integer firstArg = integers[0];
            for (Integer i = 0; i < firstArg; i++) {
                if (i % 100 == 0)
                    //Calls onProgressUpdate method
                    publishProgress("" + i);
            }
            return true; // It will be passed to onPostExecute
        }

        @Override // UI
        protected void onPreExecute() { // Before Running or starting (setup ui) the main task
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override // UI
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            pb.setVisibility(View.GONE);
            if (aBoolean) {
                Toast.makeText(MainActivity.this, "DONE", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "OOPS", Toast.LENGTH_SHORT).show();
            }
        }

        @Override // UI
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tv = findViewById(R.id.ma_tv);
            tv.setText(values[0]); // Update textView
        }
    }
}
