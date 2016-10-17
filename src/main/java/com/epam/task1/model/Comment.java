package com.epam.task1.model;

import java.sql.Date;

public class Comment {

    private int id;
    private String text;
    private Date date;
    private int userId;

    public Comment(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (userId != comment.userId) return false;
        if (!text.equals(comment.text)) return false;
        return date.equals(comment.date);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + text.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
