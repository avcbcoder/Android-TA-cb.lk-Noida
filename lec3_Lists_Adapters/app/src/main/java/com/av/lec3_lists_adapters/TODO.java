package com.av.lec3_lists_adapters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class TODO extends AppCompatActivity {
    ArrayList<TASK> al = new ArrayList<>();
    Button addTask;
    EditText taskName;
    ListView lv;
    TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        // Find View By Id's
        addTask = findViewById(R.id.btn_add);
        taskName = findViewById(R.id.task_name);
        ListView lv = findViewById(R.id.todo_listView);

        todoAdapter = new TodoAdapter();
        lv.setAdapter(todoAdapter);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                al.add(new TASK(taskName.getText().toString(), System.currentTimeMillis() + ""));
                todoAdapter.notifyDataSetChanged();
                taskName.setText("");
            }
        });

    }

    public class TodoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return al.size();
        }

        @Override
        public TASK getItem(int i) {
            return al.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater li = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View inflatedView = li.inflate(R.layout.todo_item, viewGroup, false);

            TextView date = inflatedView.findViewById(R.id.todoitem_date);
            TextView name = inflatedView.findViewById(R.id.todoitem_tv);
            Button btn = inflatedView.findViewById(R.id.todoitem_btn);

            date.setText(al.get(i).getDate());
            name.setText(al.get(i).getName());

            // Most important step if we want to remove this view further
            // We will attach tag with this button(view)
            btn.setTag(i);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View thisview) {
                    // While retrieving, we will get the position
                    int pos = (int) thisview.getTag();
                    al.remove(pos);
                    todoAdapter.notifyDataSetChanged();
                }
            });

            return inflatedView;
        }
    }
}
