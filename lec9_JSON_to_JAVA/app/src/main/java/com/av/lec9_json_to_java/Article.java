package com.av.lec9_json_to_java;

import android.util.Log;

/**
 * Created by Ankit on 29-06-2018.
 */

class Article {

    String author, title, desc, url, imageUrl, date;
    Source source;

    public Article(String author, String title, String desc, String url, String imageUrl, String date, Source source) {
        this.author = author;
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.imageUrl = imageUrl;
        this.date = date;
        this.source = source;
    }

    public String toString() {
        return String.format("\n" +
                        "AUTHOR=>%s " +
                        "TITLE=>%s " + "\n" +
                        "DESC=>%s " + "\n" +
                        "URL=>%s " + "\n" +
                        "IMAGE_URL=>%s " + "\n" +
                        "DATE=>%s " + "\n" +
                        "SOURCE  => %s" + "\n",
                author, title, desc, url, imageUrl, date, source.toString());
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDate() {
        return date;
    }

    public Source getSource() {
        return source;
    }
}