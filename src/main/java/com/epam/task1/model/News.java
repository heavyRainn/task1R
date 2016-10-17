package com.epam.task1.model;

import java.sql.Date;
import java.util.List;

public class News {

    private int id;
    private String mainTitle;
    private String shortTitle;
    private String newsText;
    private Date date;
    private List<Tag> tags;
    private List<Author> authors;
    private String photo;
    private Theme theme;

    public News() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;

        News news = (News) o;

        if (mainTitle != null ? !mainTitle.equals(news.mainTitle) : news.mainTitle != null) return false;
        if (shortTitle != null ? !shortTitle.equals(news.shortTitle) : news.shortTitle != null) return false;
        if (photo != null ? !photo.equals(news.photo) : news.photo != null) return false;
        return theme == news.theme;

    }

    @Override
    public int hashCode() {
        int result = mainTitle != null ? mainTitle.hashCode() : 0;
        result = 31 * result + (shortTitle != null ? shortTitle.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", mainTitle='" + mainTitle + '\'' +
                ", shortTitle='" + shortTitle + '\'' +
                ", newsText='" + newsText + '\'' +
                ", date=" + date +
                ", tags=" + tags +
                ", authors=" + authors +
                ", photo='" + photo + '\'' +
                ", theme=" + theme +
                '}';
    }
}
