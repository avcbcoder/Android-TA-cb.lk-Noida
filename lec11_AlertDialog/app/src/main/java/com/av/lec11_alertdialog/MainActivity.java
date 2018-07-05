package com.av.lec11_alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Use app.v7 support alert dialog
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button simple = findViewById(R.id.btnSimpleAlert);
        Button complex = findViewById(R.id.btnComplexAlert);

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Hello Alert Dialog")
                .setMessage("Hi this is message")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "positive button", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "negative button", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });

        // attachToRoot should be true
        final View dialog = getLayoutInflater().inflate(R.layout.custom_alert, null, true);

        final AlertDialog customDialog = new AlertDialog.Builder(this)
                .setTitle("Custom Alert dialog")
                .setView(dialog)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText email = dialog.findViewById(R.id.alert_mail);
                        EditText name = dialog.findViewById(R.id.alert_name);
                        Toast.makeText(MainActivity.this,
                                (email.getText().toString() + name.getText().toString()),
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        complex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.show();
            }
        });

    }
}
