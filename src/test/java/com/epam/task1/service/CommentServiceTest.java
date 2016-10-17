package com.epam.task1.service;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.impl.CommentDaoImpl;
import com.epam.task1.model.Comment;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @InjectMocks
    CommentService commentService;

    @Mock
    CrudDao commentDao;

    @Mock
    Comment comment;

    @Before
    public void setUp() {
        BasicConfigurator.configure();

        when(commentService.create(comment)).thenReturn(true);
        when(commentService.read()).thenReturn(mock(List.class));
        when(commentService.create(comment)).thenReturn(true);
        when(commentService.delete(1)).thenReturn(true);

        when(commentDao.create(comment)).thenReturn(true);
        when(commentDao.read()).thenReturn(mock(List.class));
        when(commentDao.create(comment)).thenReturn(true);
        when(commentDao.delete(1)).thenReturn(true);
    }

    @Test
    public void testCommentServiceCreate() {
        commentService.create(comment);
        verify(commentDao).create(comment);

        Assert.assertEquals(commentService.create(comment),commentDao.create(comment));
    }

    @Test
    public void testCommentServiceRead() throws SQLException {
        commentService.read();
        verify(commentDao).read();

        Assert.assertEquals(commentService.read(),commentDao.read());
    }

    @Test
    public void testCommentServiceUpdate() {
        commentService.update(comment);
        verify(commentDao).update(comment);

        Assert.assertEquals(commentService.update(comment),commentDao.update(comment));
    }

    @Test
    public void testCommentServiceDelete() {
        int intStub = anyInt();

        commentService.delete(intStub);
        verify(commentDao).delete(intStub);

        Assert.assertEquals(commentService.delete(intStub),commentDao.delete(intStub));
    }
}
