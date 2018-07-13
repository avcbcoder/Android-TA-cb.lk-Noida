package com.av.lec15_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.av.lec15_sqlite.constants.*;

/**
 * Created by Ankit on 12-07-2018.
 */

public class TaskDB extends SQLiteOpenHelper {

//    public TaskDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public TaskDB(Context context) {
        super(context, "taskdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {// Called when DB is created (i.e when version is updated)
        String query = "CREATE TABLE task " +
                "(" +
                "id INTEGER PRIMARY KEY," +
                " tasktitle TEXT NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    long insertTask(Task task) {
        // SQL injection
        String insertQuery = "Insert into task values" + constants.LBR +
                task.getId() +
                constants.COMMA +
                task.getName() +
                " );";
        // ContentValues will check the values to save from SQL injection
        ContentValues contentValues = new ContentValues();// Like a Bundle
        contentValues.put("id", task.getId());
        contentValues.put("tasktitle", task.getName());
        Long pos = getWritableDatabase() // Insert returns {position/id/primary key} where it is student
                .insert(TABLE_NAME,
                        null,
                        contentValues);
        return pos;
    }

    Task getTaskWithID(Long id) {
        return null;
    }

    ArrayList<Task> getAllTasks() {
//        TaskDB taskDB = new TaskDB(
//                this,
//                "taskdb",// A new database is created with this name itself if it does'nt exist
//                null,
//                1);
        ArrayList<Task>al=new ArrayList<>();
        String[] projection = new String[]{constants.COLUMN_ID, constants.COLUMN_Title};
        Cursor c = getReadableDatabase().query(TABLE_NAME,
                projection,
                null,
                null,//like if conditions
                null,
                null,
                null);

        while(c.moveToNext()){
            Long id=c.getLong(c.getColumnIndex(COLUMN_ID.trim()));
            String title=c.getString(c.getColumnIndex(COLUMN_Title));
            al.add(new Task(id,title));
        }
        return al;
    }
}