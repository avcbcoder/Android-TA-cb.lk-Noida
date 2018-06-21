package com.av.lec2_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button btn, intent, chooser;
    WebView wv;
    LinearLayout ll;
    Toolbar mtool;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtool = findViewById(R.id.chrome_toolbar);
        setActionBar(mtool);

        //Find view by ids
        chooser = findViewById(R.id.ma_chooser);
        et = findViewById(R.id.ma_et);
        btn = findViewById(R.id.ma_btn);
        wv = findViewById(R.id.ma_wv);
        intent = findViewById(R.id.ma_intent);
        ll = findViewById(R.id.ma_ll);

        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);// Pinch to zoom // Don't put this is scrollview
        wv.getSettings().setDisplayZoomControls(true);// Zoom controlls
        et.setInputType(InputType.TYPE_NULL);
        et.setText("google.com");

        // Set new webViewClient so that it can load links without redirecting
        wv.setWebViewClient(new myWebClient());

        //OnClickListeners------------------
        setOCL();

        Intent i = getIntent();
        if (i != null) {
            //Initially the launcher calls intent to open the app Intent.Action_MAIN so its not null
            if (i.getAction() != Intent.ACTION_MAIN) {
                et.setVisibility(View.GONE);
                ll.setVisibility(View.GONE);
                intent.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
                chooser.setVisibility(View.GONE);
                wv.loadUrl(i.getDataString());
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (wv.canGoBack())
            wv.goBack();
        else
            super.onBackPressed();
    }

    private void setOCL() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setVisibility(View.GONE);
                chooser.setVisibility(View.GONE);
                String browse = "https://www." + et.getText().toString();
                Uri uri = Uri.parse(browse);
                wv.loadUrl(browse);
            }
        });

        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
            }
        });

        intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent();
                    i.setAction("myOwnAction");
                    i.putExtra("msg", "this is the text");
                    // Dont set data like this : It will give IntentNotFound error
                    // Since URI are also checked in manifest file
                    //i.setData(Uri.parse("this is the text"));
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "No Intent Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                    String title = "Chooser Title";
                    Intent chooser = Intent.createChooser(sendIntent, title);
                    startActivity(chooser);
                    // Open https browser
                    // startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")), "TITLE"));
                    // Open Dialer
                    // startActivity(Intent.createChooser(new Intent(Intent.ACTION_DIAL), title));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "No Intent Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class myWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
