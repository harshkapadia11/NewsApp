package com.example.news;

public class Model {
    private String author, title, description, url, urlimg, publish, id;

    public Model(String author, String title, String description, String url, String id, String urlimg, String publish) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.id = id;
        this.urlimg = urlimg;
        this.publish = publish;

    }

    public Model() {


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
