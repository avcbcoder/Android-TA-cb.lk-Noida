package com.av.lec11_broadcastrecievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyImplicitReceiver extends BroadcastReceiver {
    // Manifest cant be used to define most implicit broadcasts since they are not specific to our app
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Implicit called", Toast.LENGTH_SHORT).show();
    }
}
