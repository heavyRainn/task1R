package com.epam.task1.dao.impl;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.connectionpool.DataSource;
import com.epam.task1.model.Tag;
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
public class TagDaoImplTest {

    @Configuration
    @ComponentScan("com.epam.task1")
    static class ContextConfiguration {
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    private CrudDao<Tag> tagDao;

    @Test
    @Rollback
    public void testCreate() {
        String text = "#SIMPLIFY";

        List<Tag> tags;
        tags = tagDao.read();
        int sizeBefore = tags.size();

        Tag tag = new Tag(text);
        tagDao.create(tag);

        tags = tagDao.read();
        int sizeAfter = tags.size() - 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
        Assert.assertTrue(tags.get(0) instanceof Tag);
    }

    @Test
    @Rollback
    public void testRead() {
        List<Tag> tags;
        tags = tagDao.read();

        Assert.assertFalse(tags.isEmpty());
        Assert.assertTrue(tags.get(0) instanceof Tag);
    }

    @Test
    @Rollback
    public void testUpdate() {
        int tagNum = 3;
        String tagText = "#SEVENTEENEXX";

        List<Tag> tags;
        tags = tagDao.read();

        Tag tagBefore = new Tag(tagText);
        tagBefore.setId(tags.get(tagNum).getId());

        Assert.assertTrue(tagDao.update(tagBefore));

        tags = tagDao.read();

        Tag tagAfter = tags.get(tagNum);

        Assert.assertEquals(tagBefore, tagAfter);
    }

    @Test
    @Rollback
    public void testDelete() {
        int tagNum = 2;

        List<Tag> tags;
        tags = tagDao.read();

        Tag tag = tags.get(tagNum);
        int tagId = tag.getId();
        int sizeBefore = tags.size();

        Assert.assertTrue(tagDao.delete(tagId));

        tags = tagDao.read();

        int sizeAfter = tags.size() + 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
    }
}
