package com.epam.task1.model;

import java.sql.Date;

public class News {

    private int id;
    private String mainTitle;
    private String shortTitle;
    private String newsText;
    private Date date;
    private String photo;
    private Theme theme;

    public int getId() {
        return id;
    }

    public Theme getTheme() {
        return theme;
    }

    public String getPhoto() {
        return photo;
    }

    public Date getDate() {
        return date;
    }

    public String getNewsText() {
        return newsText;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        return date != null ? date.equals(news.date) : news.date == null;

    }

    @Override
    public int hashCode() {
        int result = mainTitle != null ? mainTitle.hashCode() : 0;
        result = 31 * result + (shortTitle != null ? shortTitle.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
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
                ", photo='" + photo + '\'' +
                ", theme=" + theme +
                '}';
    }
}
