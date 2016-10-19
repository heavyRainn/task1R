package com.epam.task1.dao;

import java.util.List;

public interface CrudDao<T> {

    boolean create(T t);

    List<T> read();

    List<T> read(int idNews);

    boolean update(T t);

    boolean delete(int id);

}
