package com.av.lec9_json_to_java;

import android.util.Log;

/**
 * Created by Ankit on 29-06-2018.
 */

class Source {

    String id, name;

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID=>" + id + " NAME=>" + name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
