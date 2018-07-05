package com.av.lec11_broadcastrecievers;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button start, stop, explicit, implicit;
    MyBroadcastReceiver myBroadcastReceiver;
    IntentFilter filter;
    MyImplicitReceiver myImplicitReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dynamic Broadcast receivers
        myBroadcastReceiver = new MyBroadcastReceiver();
        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        start = findViewById(R.id.btnStart);
        stop = findViewById(R.id.btnStop);
        explicit = findViewById(R.id.btnExplicit);
        implicit = findViewById(R.id.btnImplicit);

        // adding custom action receiver
        myImplicitReceiver = new MyImplicitReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.av");
        registerReceiver(myImplicitReceiver, intentFilter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerReceiver(myBroadcastReceiver, filter); // registered for whole app
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unregisterReceiver(myBroadcastReceiver);
            }
        });

        explicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), MyExplicitReceiver.class);
                sendBroadcast(i);
            }
        });

        implicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("com.av");
                // i.setAction("android.intent.action.HEADSET_PLUG"); // System actions not allowed
                sendBroadcast(i);
            }
        });

    }
}
