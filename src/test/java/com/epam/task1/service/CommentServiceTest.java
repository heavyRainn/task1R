package com.epam.task1.service;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.impl.CommentDaoImpl;
import com.epam.task1.model.Comment;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommentServiceTest {

    @Autowired
    @InjectMocks
    CommentService commentService;
    CrudDao commentDao;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();

        commentDao = mock(CommentDaoImpl.class);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCommentServiceCreate() {
        Comment comment = mock(Comment.class);

        commentService.create(comment);
        verify(commentDao).create(comment);
    }

    @Test
    public void testCommentServiceRead() throws SQLException {
        commentService.read();
        verify(commentDao).read();
    }

    @Test
    public void testCommentServiceUpdate() {
        Comment comment = any(Comment.class);

        commentService.update(comment);
        verify(commentDao).update(comment);
    }

    @Test
    public void testCommentServiceDelete() {
        int intStub = anyInt();

        commentService.delete(intStub);
        verify(commentDao).delete(intStub);
    }
}
