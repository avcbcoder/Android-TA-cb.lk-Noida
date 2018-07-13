package com.av.lec16_room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Ankit on 13-07-2018.
 */

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    abstract TaskDao getTaskDao();

}
