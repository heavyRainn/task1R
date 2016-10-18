package com.epam.task1.dao.impl;

import com.epam.task1.config.NewspaperConfigTest;
import com.epam.task1.dao.CrudDao;
import com.epam.task1.model.Comment;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NewspaperConfigTest.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:db.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:tearDown.xml", type = DatabaseOperation.DELETE_ALL)
public class CommentDaoImplTest {

    @Autowired
    private CrudDao<Comment> commentDao;

    private static final String COMMENT_TEXT_CREATE = "TESTCASER DBUNIT";
    private static final String COMMENT_TEXT_UPDATE = "ALIENS BEST";

    @Test
    @Rollback
    public void testCreate() {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        List<Comment> comments = commentDao.read();
        int sizeBefore = comments.size();

        Comment comment = new Comment(COMMENT_TEXT_CREATE, sqlDate);
        commentDao.create(comment);

        comments = commentDao.read();
        int sizeAfter = comments.size() - 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
        Assert.assertTrue(comments.get(0) instanceof Comment);
    }

    @Test
    @Rollback
    public void testRead() {
        List<Comment> comments = commentDao.read();

        Assert.assertFalse(comments.isEmpty());
        Assert.assertTrue(comments.get(0) instanceof Comment);
    }

    @Test
    @Rollback
    public void testUpdate() {
        List<Comment> comments = commentDao.read();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Comment commentBefore = new Comment(COMMENT_TEXT_UPDATE, sqlDate);
        commentBefore.setId(comments.get(0).getId());

        Assert.assertTrue(commentDao.update(commentBefore));

        comments = commentDao.read();

        Comment commentAfter = comments.get(0);

        Assert.assertEquals(commentBefore.getText(), commentAfter.getText());
    }

    @Test
    @Rollback
    public void testDelete() {
        List<Comment> comments = commentDao.read();

        Comment comment = comments.get(0);
        int commentId = comment.getId();
        int sizeBefore = comments.size();

        Assert.assertTrue(commentDao.delete(commentId));

        comments = commentDao.read();

        int sizeAfter = comments.size() + 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
    }

}
