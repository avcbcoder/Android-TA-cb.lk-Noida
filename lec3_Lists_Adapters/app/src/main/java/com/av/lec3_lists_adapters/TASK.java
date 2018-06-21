package com.av.lec3_lists_adapters;

/**
 * Created by Ankit on 22-06-2018.
 */

public class TASK {
//    data class TASK(var name: String, var date: String)
    String name,date;

    public TASK(String name, String date) {
        this.name = name;
        this.date = date;
    }
    TASK(){

    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
