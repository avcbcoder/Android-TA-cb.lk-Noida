package com.av.lec3_lists_adapters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedList extends AppCompatActivity {
    ArrayList<Student> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_list);

        int size = 30;
        while (size-- > 0) {
            Student curr = Student.newStudent();
            al.add(curr);
        }

        ListView lv = findViewById(R.id.detailed_list);
        lv.setAdapter(new CustomAdapter());
    }

    public class CustomAdapter extends BaseAdapter {

        View[] allViews = new View[al.size() + 10];
        boolean[] visit = new boolean[al.size() + 10];

        @Override
        public int getCount() {
            return al.size();
        }

        @Override
        public Student getItem(int i) {
            return al.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            // It is working here since we have access to context
            // If we will make another custom adapter which is not inner class then we will have to pass
            // context,arraylist and then get context.getLayoutInflator();
            LayoutInflater li = getLayoutInflater();
            // We can do this too
            //li=(LayoutInflater)( getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE));
            View itemView;

            /* Method 1 -----------------
            if (visit[ i]) {
                itemView = allViews[i];
            } else {
                itemView = li.inflate(R.layout.student_item, parent, false);
                allViews[i] = itemView;
                visit[i] = true;
            }
            */

            /* Method 2------------------
            if(view==null){
                itemView = li.inflate(R.layout.student_item, parent, false);
            }else{
                itemView=view;
            }
            */

            /* Method 3-----------------
            It takes a lot of time since it inflates view each time
             */
            itemView = li.inflate(R.layout.student_item, parent, false);

            TextView tvName = (TextView) itemView.findViewById(R.id.tvName);
            TextView tvAge = (TextView) itemView.findViewById(R.id.tvAge);
            TextView tvMarks = (TextView) itemView.findViewById(R.id.tvMarks);
            Student curr = getItem(i);
            tvName.setText(curr.getName() + "");
            tvAge.setText(curr.getAge() + "");
            tvMarks.setText(curr.getMarks() + "");
            return itemView;
            // parent is viewgroup => It is used to save some CPU time , already calculated width & height
            // is used in case of viewgroup
        }
    }
}
