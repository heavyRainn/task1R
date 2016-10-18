package com.epam.task1.model;

import static javax.swing.text.StyleConstants.Size;

public class Author {

    private int id;
    private String name;
    private String surname;

    public Author(String name, String surname) {
        this.surname = surname;
        this.name = name;
    }

    public void setName(String name) {
        name = name;
    }

    public void setSurname(String surname) {
        surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        if (id != author.id) return false;
        if (!name.equals(author.name)) return false;
        return surname.equals(author.surname);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                '}';
    }
}
