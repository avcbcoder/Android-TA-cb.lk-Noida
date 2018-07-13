package com.av.lec16_room;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Ankit on 13-07-2018.
 */

public class TaskApplication extends Application {

    public static AppDatabase appDatabase;

    public static Context ctx;// Warning

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = this;
        // No need of making context as Static now
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "task-db")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public static AppDatabase getDb() {
//        if (appDatabase == null) {
//            appDatabase = Room.databaseBuilder(ctx,
//                    AppDatabase.class,
//                    "task-db")
//                    .build();
//        }
        return appDatabase;
    }
}
