package com.av.lec16_room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Ankit on 13-07-2018.
 */

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task_table") // compile time checks are done here
    LiveData<List<Task>>getAllTasks();

    @Query("SELECT * FROM TASK_TABLE WHERE ID=:id")
    Task taskWithID(int id);

    @Insert
    void insert(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

//    @Insert
//    void insertTaskList(List<Task> allTask);

    @Insert
    void insertTaskList(Task...allTask);

}
