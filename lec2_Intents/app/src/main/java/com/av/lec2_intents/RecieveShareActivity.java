package com.av.lec2_intents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class RecieveShareActivity extends AppCompatActivity {
    public static final String TAG = "RecieveShareActivity";
    public static Context context;
    private ImageView setRecievedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_share);

        context = this;

        setRecievedImage = findViewById(R.id.rsa_iv);

        Intent i = getIntent();
        String action = i.getAction();
        String type = i.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.equals("text/plain")) {
                textRecieved(i);
                Toast.makeText(this, "TEXT", Toast.LENGTH_SHORT).show();
            } else if (type.startsWith("image/")) {
                imageRecieved(i);
                Toast.makeText(this, "IMAGE", Toast.LENGTH_SHORT).show();
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                multipleImageRecieved(i);
                Toast.makeText(this, "Multiple IMAGE", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Other intents
        }
    }

    private void multipleImageRecieved(Intent i) {
    }

    private void imageRecieved(Intent i) {
        Log.e(TAG, "imageRecieved: " + i.getData().toString());
    }

    private void textRecieved(Intent i) {
    }


}
