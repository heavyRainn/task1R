package com.epam.task1.service;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.exception.ServiceException;
import com.epam.task1.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private CrudDao<Tag> tagDao;

    public boolean create(Tag tag) throws ServiceException {
        return tagDao.create(tag);
    }

    public List<Tag> read() throws ServiceException {
        return tagDao.read();
    }

    public List<Tag> read(int idNews) throws ServiceException {
        return tagDao.read(idNews);
    }

    public boolean update(Tag tag) throws ServiceException {
        return tagDao.update(tag);
    }

    public boolean delete(int id) throws ServiceException {
        return tagDao.delete(id);
    }

}
