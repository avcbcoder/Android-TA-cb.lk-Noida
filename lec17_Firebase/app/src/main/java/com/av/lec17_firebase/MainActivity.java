package com.av.lec17_firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// By default all get/push request are async
public class MainActivity extends AppCompatActivity {
    EditText et;
    Button btn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.etText);
        btn = findViewById(R.id.btnPush);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //databaseReference.child("child").setValue(et.getText().toString());
                String uid = databaseReference.push().getKey();
                databaseReference.child(uid).setValue(new Task(uid, et.getText().toString(), System.currentTimeMillis() + ""));
                et.setText("");
            }
        });

        /*
        * there are two ways
        * 1. addValueEventListenere
        * 2. addListenerForSingleValueEvent + addChildEventListener
        * */

        /*
        // execute one extra time i.e it will run n+1 times in case of n updates
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                for (DataSnapshot ds : dataSnapshots) {
                    Task curr = ds.getValue(Task.class);
                    Log.e("----------", "onDataChange: " + curr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */


        // called only one time
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                for (DataSnapshot ds : dataSnapshots) {
                    Task curr = ds.getValue(Task.class);
                    Log.e("----------", "onDataChange: " + curr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // called n times on n updates, more efficient
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Task currTask=dataSnapshot.getValue(Task.class);
                // call itemInserted on adapter instead of notifyDataSetChanged
                Log.e("--------", "onChildAdded: "+currTask );
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /*
        * We will have to save the anonymous object to be able to remove the event listener
        * */

    }
}
