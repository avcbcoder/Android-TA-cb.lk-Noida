package com.av.lec15_sqlite;

/**
 * Created by Ankit on 12-07-2018.
 */

public class Task {
    Long id;
    String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Task(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
