package com.av.lec16_room;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.arch.lifecycle.Observer;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task t = new Task("TITLETITLE", "DESDES", false);
                AppDatabase db = TaskApplication.getDb();
                db.getTaskDao().insert(t);
//                Toast.makeText(MainActivity.this, db.getTaskDao().getAllTasks().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        TaskApplication.getDb().getTaskDao().getAllTasks()
                .observe(this, new Observer<List<Task>>() {
                    @Override
                    public void onChanged(@Nullable List<Task> tasks) {
                        Log.e("TAG", "onChanged: size:"+ tasks.size());
                    }
                });
    }
}
