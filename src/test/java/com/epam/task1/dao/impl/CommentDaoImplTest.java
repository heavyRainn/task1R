package com.epam.task1.dao.impl;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.connectionpool.DataSource;
import com.epam.task1.model.Comment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class CommentDaoImplTest {

    @Configuration
    @ComponentScan("com.epam.task1")
    static class ContextConfiguration {
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    private CrudDao<Comment> commentDao;

    @Test
    @Rollback
    public void testCreate() {
        String text = "TESTCASE DBUNIT";

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        List<Comment> comments;
        comments = commentDao.read();
        int sizeBefore = comments.size();

        Comment comment = new Comment(text, sqlDate);
        commentDao.create(comment);

        comments = commentDao.read();
        int sizeAfter = comments.size() - 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
        Assert.assertTrue(comments.get(0) instanceof Comment);
    }

    @Test
    @Rollback
    public void testRead() {
        List<Comment> comments;
        comments = commentDao.read();

        Assert.assertFalse(comments.isEmpty());
        Assert.assertTrue(comments.get(0) instanceof Comment);
    }

    @Test
    @Rollback
    public void testUpdate() {
        String text = "ALIENS BEST";

        List<Comment> comments;
        comments = commentDao.read();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Comment commentBefore = new Comment(text, sqlDate);
        commentBefore.setId(comments.get(10).getId());

        Assert.assertTrue(commentDao.update(commentBefore));

        comments = commentDao.read();

        Comment commentAfter = comments.get(10);

        Assert.assertEquals(commentBefore.getText(), commentAfter.getText());
    }

    @Test
    @Rollback
    public void testDelete() {
        List<Comment> comments;
        comments = commentDao.read();

        Comment comment = comments.get(18);
        int commentId = comment.getId();
        int sizeBefore = comments.size();

        Assert.assertTrue(commentDao.delete(commentId));

        comments = commentDao.read();

        int sizeAfter = comments.size() + 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
    }

}
