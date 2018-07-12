package com.av.lec15_sqlite;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TaskDB taskDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskDB = new TaskDB(this);
        long pos = taskDB.insertTask(new Task(1, "this is the task"));
        Toast.makeText(this, "Pushed @ " + pos, Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long c = System.currentTimeMillis();
                while (System.currentTimeMillis() < c + 5000) ;
                ArrayList<Task> al = taskDB.getAllTasks();
                for (Task t : al)
                    Log.e("--------", t.getId() + " " + t.getName());
            }
        }).start();
    }

}
