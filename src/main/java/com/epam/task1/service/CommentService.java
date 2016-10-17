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

    public boolean create(Comment comment) {
        return commentDao.create(comment);
    }

    public List<Comment> read() {
        return commentDao.read();
    }

    public boolean update(Comment comment) {
        return commentDao.update(comment);
    }

    public boolean delete(int id) {
        return commentDao.delete(id);
    }

}
