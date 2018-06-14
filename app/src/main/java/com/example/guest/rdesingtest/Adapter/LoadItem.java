package com.example.guest.rdesingtest.Adapter;

//Load JSON data to cardview...

public class LoadItem {

    public String title_story;
    public String author;
    public String created_at;
    public String url_access;

    public LoadItem(String title_story, String author, String created_at, String url_access) {
        this.title_story = title_story;
        this.author = author;
        this.created_at = created_at;
        this.url_access = url_access;
    }

    public String getTitle_story() {
        return title_story;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUrl_access(){return url_access;}
}
