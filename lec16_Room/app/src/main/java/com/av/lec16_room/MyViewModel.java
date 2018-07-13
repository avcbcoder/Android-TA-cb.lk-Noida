package com.av.lec16_room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by Ankit on 13-07-2018.
 */

// It is like a temporary data which persist till activity is killed
// Don't make changes to views from here, just store the data in viewModel
public class MyViewModel extends ViewModel {
    private LiveData<List<Task>> taskListLiveData;

    LiveData<List<Task>> getTaskListLiveData() {
        if (taskListLiveData == null) // Since calling this takes time
            taskListLiveData = TaskApplication.getDb().getTaskDao().getAllTasks();
        return taskListLiveData;
    }


}
