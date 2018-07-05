package com.av.lec11_broadcastrecievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ankit on 05-07-2018.
 */

// Dynamic recievers work only when app is running
// App should be launched once so that this broadcast can be registered in system
public class MyBroadcastReciever extends BroadcastReceiver { // Static Broadcast
    @Override
    public void onReceive(Context context, Intent intent) {
        String s=intent.getAction();
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
