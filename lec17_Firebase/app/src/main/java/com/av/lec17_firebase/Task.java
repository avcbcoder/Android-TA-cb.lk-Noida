package com.av.lec17_firebase;

/**
 * Created by Ankit on 16-07-2018.
 */

public class Task {
    String id;
    String title;
    String time;

    Task() {
    }

    public Task(String id, String title, String time) {
        this.id = id;
        this.title = title;
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return this.getId() + "   " + this.getTime() + "   " + this.getTitle()+"\n";
    }
}
