package com.example.admin.guardianinsyder;

public class News {
    private String title;
    private String author;
    private String genre;
    private String date;
    private String url;


    public News(String title,String author,String date,String url,String genre){
        this.title=title;
        this.author=author;
        this.date=date;
        this.url=url;
        this.genre=genre;

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getGenre() {
        return genre;
    }
}


