package com.epam.task1.service;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.exception.DaoException;
import com.epam.task1.exception.ServiceException;
import com.epam.task1.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private CrudDao<Author> authorDao;

    public boolean create(Author author) throws ServiceException {
        return authorDao.create(author);
    }

    public List<Author> read() throws ServiceException {
        return authorDao.read();
    }

    public List<Author> read(int idNews) throws ServiceException {
        return authorDao.read(idNews);
    }

    public boolean update(Author author) throws ServiceException {
        return authorDao.update(author);
    }

    public boolean delete(int id) throws ServiceException {
        return authorDao.delete(id);
    }

}
