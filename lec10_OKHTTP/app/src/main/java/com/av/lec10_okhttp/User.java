package com.av.lec10_okhttp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ankit on 03-07-2018.
 */

public class User {
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    @Override
    public String toString() {
        return this.login + " " + this.htmlUrl + "\n\n";
    }
}
