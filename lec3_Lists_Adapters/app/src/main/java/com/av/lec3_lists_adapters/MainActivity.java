package com.av.lec3_lists_adapters;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> al = new ArrayList<>(Arrays.asList("Hello", "Helo"));
    int[] colors = {Color.CYAN, Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE};
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populate(al);

        ListView lv = findViewById(R.id.ma_listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item,
                R.id.tv,
                al);
        lv.setAdapter(arrayAdapter);

        // Set On Item click listeners on each view
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, al.get(i) + "", Toast.LENGTH_SHORT).show();
                int idx = r.nextInt(colors.length);
                view.setBackgroundColor(colors[idx]);
            }
        });


    }

    private void populate(ArrayList<String> al) {
        int len = r.nextInt(10) + 1;
        int m = 30;
        while (m-- > 0) {
            String s = "";
            for (int i = 0; i < len; i++)
                s += (char) ('a' + r.nextInt(26));
            al.add(s);
        }
    }
}
