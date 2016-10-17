package com.epam.task1.dao;

import com.epam.task1.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> extends Dao {

    boolean create(T t);

    List<T> read();

    List<T> read(int idNews);

    boolean update(T t);

    boolean delete(int id);

}
