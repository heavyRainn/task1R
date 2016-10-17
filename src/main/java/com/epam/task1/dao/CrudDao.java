package com.epam.task1.dao;

import com.epam.task1.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> extends Dao {

    boolean create(T t) throws DaoException;

    List<T> read() throws DaoException;

    List<T> read(int idNews) throws DaoException;

    boolean update(T t) throws DaoException;

    boolean delete(int id) throws DaoException;

}
