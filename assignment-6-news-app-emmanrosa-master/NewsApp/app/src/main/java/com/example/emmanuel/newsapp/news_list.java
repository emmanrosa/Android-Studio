package com.example.emmanuel.newsapp;

/**
 * Created by emmanuel on 10/19/17.
 */

public class news_list {
    String titles;
    String authors;
    String urls;
    String dates;
    String sections;

    public news_list(String title, String author, String url, String date, String section) {
        titles = title;
        authors = author;
        urls = url;
        dates = date;
        sections = section;
    }

    public String getTitle() {
        return titles;
    }

    public String getAuthor() {
        return authors;
    }

    public String getUrl() {
        return urls;
    }

    public String getDate() {
        return dates;
    }

    public String getSection() {
        return sections;
    }

}
