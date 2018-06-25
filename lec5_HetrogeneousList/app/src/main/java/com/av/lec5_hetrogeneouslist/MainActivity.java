package com.av.lec5_hetrogeneouslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Object> objects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objects.add(new TextMessage("Alice White"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/88.jpg"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/13.jpg"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/44.jpg"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/45.jpg"));
        objects.add(new TextMessage("Alice White randomuser"));
        objects.add(new TextMessage("Alice"));
        objects.add(new TextMessage("White"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/33.jpg"));
        objects.add(new TextMessage("Hello"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/32.jpg"));
        objects.add(new TextMessage("Hello World"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/34.jpg"));
        objects.add(new TextMessage("Hello World"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/77.jpg"));
        objects.add(new TextMessage("Charlie Barrett"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/90.jpg"));
        objects.add(new TextMessage("Charlie"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/87.jpg"));
        objects.add(new TextMessage("Barrett"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/80.jpg"));
        objects.add(new TextMessage("Harshit Dwivedi"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/66.jpg"));
        objects.add(new TextMessage("Harshit"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/65.jpg"));
        objects.add(new TextMessage("Dwivedi"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/62.jpg"));
        objects.add(new TextMessage("Android"));
        objects.add(new ImageMessage("https://randomuser.me/api/portraits/women/60.jpg"));
        RecyclerView recyclerView = findViewById(R.id.rvList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        HetroAdapter adapter = new HetroAdapter(objects, this);

        recyclerView.setAdapter(adapter);

    }
}
