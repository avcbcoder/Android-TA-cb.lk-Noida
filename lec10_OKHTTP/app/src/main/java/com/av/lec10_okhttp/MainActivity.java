package com.av.lec10_okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button btnFetch;
    TextView tvResponse, tvFirstUser;

    public static final MediaType JSON
            = MediaType.parse("application/json");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFetch = findViewById(R.id.btnFetch);
        tvResponse = findViewById(R.id.tvResponse);
        tvFirstUser = findViewById(R.id.tvFirstUser);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.github.com/search/users?q=harshit";
                //String postUrl = "http://ptsv2.com/t/4r18i-1530611131/post";
                makeNetworkCall(url);
            }
        });
    }

    private void makeNetworkCall(String url) {
        // Make network call
        OkHttpClient okHttpClient = new OkHttpClient();

//        RequestBody requestBody= RequestBody.create(JSON,"{hello:world}");

        Request request = new Request
                .Builder()
//                .post()
                .url(url)
                .build();
        Call okkhttpCall = okHttpClient.newCall(request);
        // execute will not work on UI thread, so we will use enqueue
        // okkhttpCall.execute();

        // Enqueue will run on different thread
        okkhttpCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Request was unsuccessfull
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // success
                // we can get url for which call was made using call.url()
                // we can recall this using call.enqueue
                final String result = response.body().string(); // call string method only not toString
                // You can use response.body() only once since it implements closeable
                int code = response.code();

                // run Gson on other thread since it runs loop to get all object internally
                Gson gson = new Gson();
                final ApiResponse apiResponse = gson.fromJson(result, ApiResponse.class);

                Log.e("CALL", "onResponse: " + code + result);

                // we can modify this method using broadcast receiver , RxJAVA, services
                (MainActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        tvResponse.setText(result);
                        //tvFirstUser.setText();
                        ArrayList<User> users = apiResponse.getItems();
                        User firstUser = users.get(0);
                        String loginOfFirstUser = firstUser.getLogin();
                        //String firstUserToJson = gson.toJson(firstUser);
                        tvResponse.setText(users.toString());
                        tvFirstUser.setText(loginOfFirstUser);
                    }
                });

            }
        });
    }
}
