package com.example.admin.guardianinsyder;

public class News {
    private String title;
    private String genre;
    private String url;
    private String date;

    public News(String title,String genre,String date,String url){
        this.title=title;
        this.genre=genre;
        this.url=url;
        this.date=date;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }
}
