package com.epam.task1.dao.impl;

import com.epam.task1.config.NewspaperConfigTest;
import com.epam.task1.dao.CrudDao;
import com.epam.task1.model.Tag;
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
public class TagDaoImplTest {

    @Autowired
    private CrudDao<Tag> tagDao;

    private static final String TAG_TEXT_CREATE = "#SIMPLIFY";
    private static final String TAG_TEXT_UPDATE = "#SEVENTEENEXX";

    @Test
    @Rollback
    public void testCreate() {
        List<Tag> tags = tagDao.read();
        int sizeBefore = tags.size();

        Tag tag = new Tag(TAG_TEXT_CREATE);
        tagDao.create(tag);

        tags = tagDao.read();
        int sizeAfter = tags.size() - 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
        Assert.assertTrue(tags.get(0) instanceof Tag);
    }

    @Test
    @Rollback
    public void testRead() {
        List<Tag> tags = tagDao.read();

        Assert.assertFalse(tags.isEmpty());
        Assert.assertTrue(tags.get(0) instanceof Tag);
    }

    @Test
    @Rollback
    public void testUpdate() {
        int tagNum = 0;

        List<Tag> tags = tagDao.read();

        Tag tagBefore = new Tag(TAG_TEXT_UPDATE);
        tagBefore.setId(tags.get(tagNum).getId());

        Assert.assertTrue(tagDao.update(tagBefore));

        tags = tagDao.read();

        Tag tagAfter = tags.get(tagNum);

        Assert.assertEquals(tagBefore, tagAfter);
    }

    @Test
    @Rollback
    public void testDelete() {
        int tagNum = 0;

        List<Tag> tags = tagDao.read();

        Tag tag = tags.get(tagNum);
        int tagId = tag.getId();
        int sizeBefore = tags.size();

        Assert.assertTrue(tagDao.delete(tagId));

        tags = tagDao.read();

        int sizeAfter = tags.size() + 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
    }
}
