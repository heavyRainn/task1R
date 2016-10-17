package com.epam.task1.dao.impl;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.connectionpool.DataSource;
import com.epam.task1.model.Author;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static javax.management.Query.not;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static sun.nio.cs.Surrogate.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class AuthorDaoImplTest {

    @Configuration
    @ComponentScan("com.epam.task1")
    static class ContextConfiguration {
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    private CrudDao<Author> authorDao;

    @Test
    @Rollback
    public void testCreate() {
        String name = "Yaehal";
        String surname = "Natelege";
        List<Author> authors;
        authors = authorDao.read();
        int sizeBefore = authors.size();

        Author author = new Author(name, surname);
        authorDao.create(author);

        authors = authorDao.read();
        int sizeAfter = authors.size() - 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
        Assert.assertTrue(authors.get(0) instanceof Author);
    }

    @Test
    @Rollback
    public void testRead() {
        List<Author> authors;
        authors = authorDao.read();

        Assert.assertFalse(authors.isEmpty());
        Assert.assertTrue(authors.get(0) instanceof Author);
    }

    @Test
    @Rollback
    public void testUpdate() {
        List<Author> authors;
        authors = authorDao.read();

        Author authorBefore = new Author("Zigmund","Freid");
        authorBefore.setId(authors.get(10).getId());

        Assert.assertTrue(authorDao.update(authorBefore));

        authors = authorDao.read();

        Author authorAfter = authors.get(10);

        Assert.assertEquals(authorBefore, authorAfter);
    }

    @Test
    @Rollback
    public void testDelete() {
        List<Author> authors;
        authors = authorDao.read();

        Author autor = authors.get(22);
        int autorId = autor.getId();
        int sizeBefore = authors.size();

        Assert.assertTrue(authorDao.delete(autorId));

        authors = authorDao.read();

        int sizeAfter = authors.size() + 1;

        Assert.assertEquals(sizeBefore, sizeAfter);
    }
}
