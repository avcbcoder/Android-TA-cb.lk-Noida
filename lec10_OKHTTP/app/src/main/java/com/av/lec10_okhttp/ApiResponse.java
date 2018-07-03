package com.av.lec10_okhttp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ankit on 03-07-2018.
 */

public class ApiResponse {

    //    @SerializedName("incomplete_results")
//    private String incompleteResults;
//
//    @SerializedName("totalCount")
//    private String totalCount;
//
//    public void setIncompleteResults(String incompleteResults) {
//        this.incompleteResults = incompleteResults;
//    }
//
//    public void setTotalCount(String totalCount) {
//        this.totalCount = totalCount;
//    }
//
//    public String getIncompleteResults() {
//        return incompleteResults;
//    }
//
//    public String getTotalCount() {
//        return totalCount;
//    }
    private ArrayList<User> items;

    public ArrayList<User> getItems() {
        return items;
    }
}
