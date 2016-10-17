package com.epam.task1.service;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.exception.DaoException;
import com.epam.task1.exception.ServiceException;
import com.epam.task1.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CrudDao<Comment> commentDao;

    public boolean create(Comment comment) throws ServiceException {
        return commentDao.create(comment);
    }

    public List<Comment> read() throws ServiceException {
        return commentDao.read();
    }

    public boolean update(Comment comment) throws ServiceException {
        return commentDao.update(comment);
    }

    public boolean delete(int id) throws ServiceException {
        return commentDao.delete(id);
    }

}
