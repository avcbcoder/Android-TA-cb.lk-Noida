package com.av.lec9_json_to_java;

import java.util.ArrayList;

/**
 * Created by Ankit on 29-06-2018.
 */

public class Result {
    String status;
    Integer totalResults;
    ArrayList<Article> articles;

    public Result(String status, Integer totalResults, ArrayList<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
