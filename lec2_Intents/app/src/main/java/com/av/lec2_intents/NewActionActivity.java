package com.av.lec2_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NewActionActivity extends AppCompatActivity {
    public static final String TAG = "NewActionActivity";

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_action);
        Intent i = getIntent();
        if (i != null) {
            if (i.getAction().toString().equals("myOwnAction")) {
                Log.e(TAG, i.getAction().toString());
                String s = i.getStringExtra("msg");
                tv = findViewById(R.id.naa_tv);
                tv.setText(s);
            } else {

            }
        }
    }
}
