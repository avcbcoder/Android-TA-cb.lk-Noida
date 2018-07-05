package com.av.lec11_broadcastokhttp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String DOWNLOAD = "action.download";
    public static final String NAME = "name";
    public static final String EMAIL = "email";

    Button btnSubmit;
    EditText etMail, etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyPostReceiver.ctx = MainActivity.this.getBaseContext();

        btnSubmit = findViewById(R.id.btnSubmit);
        etMail = findViewById(R.id.et_mail);
        etName = findViewById(R.id.et_name);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("---------", "onClick: ");
                Intent i = new Intent(getBaseContext(), MyPostReceiver.class);
//                i.setAction(DOWNLOAD);
                i.putExtra(NAME, etName.getText().toString());
                i.putExtra(EMAIL, etMail.getText().toString());
                sendBroadcast(i);
            }
        });
    }
}
