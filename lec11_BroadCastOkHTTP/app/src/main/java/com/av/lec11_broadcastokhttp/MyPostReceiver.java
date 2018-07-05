package com.av.lec11_broadcastokhttp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Ankit on 05-07-2018.
 */

public class MyPostReceiver extends BroadcastReceiver {
    String postUrl = "http://ptsv2.com/t/xzrak-1530787550/post";
    static Context ctx;

    @Override
    public void onReceive(final Context context, Intent intent) { // It always runs on UI thread
        String email = intent.getStringExtra(MainActivity.EMAIL);
        String name = intent.getStringExtra(MainActivity.NAME);

        // There will be no issue while sending data even if mime type is image and data is string
        // but there will be problem on server
        MediaType mediaType = MediaType.parse("text/plain");// File type which we are uploading
        RequestBody postUser = RequestBody.create(mediaType, (email + name));
        // RequestBody postUser = RequestBody.create(mediaType, ("These hoes ain't loyal HAHA"));

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(postUrl)
                .post(postUser)
                .build();
//        for (int i = 0; i < 240; i++) {
        okHttpClient.newCall(request).enqueue(new Callback() { // run on different thread
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("---------", "FAILED");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("---------", "Success");
                // Toast not working
                //Toast.makeText(ctx, response.body().string(), Toast.LENGTH_SHORT).show();
            }
        });
    }
//    }
}
