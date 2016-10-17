package com.epam.task1.service;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.impl.AuthorDaoImpl;
import com.epam.task1.model.Author;
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
public class AuthorServiceTest {

    @InjectMocks
    AuthorService authorService;

    @Mock
    CrudDao authorDao;

    @Mock
    Author author;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();

        when(authorService.create(author)).thenReturn(true);
        when(authorService.read()).thenReturn(mock(List.class));
        when(authorService.create(author)).thenReturn(true);
        when(authorService.delete(1)).thenReturn(true);

        when(authorDao.create(author)).thenReturn(true);
        when(authorDao.read()).thenReturn(mock(List.class));
        when(authorDao.create(author)).thenReturn(true);
        when(authorDao.delete(1)).thenReturn(true);
    }

    @Test
    public void testAuthorServiceCreate() {
        authorService.create(author);
        verify(authorDao).create(author);

        Assert.assertEquals(authorService.create(author), authorDao.create(author));
    }

    @Test
    public void testAuthorServiceRead() throws SQLException {
        authorService.read();
        verify(authorDao).read();

        Assert.assertEquals(authorService.read(), authorDao.read());
    }

    @Test
    public void testAuthorServiceReadById() throws SQLException {
        int intStub = anyInt();

        authorService.read(intStub);
        verify(authorDao).read(intStub);

        Assert.assertEquals(authorService.read(intStub), authorDao.read(intStub));
    }

    @Test
    public void testAuthorServiceUpdate() {
        authorService.update(author);
        verify(authorDao).update(author);

        Assert.assertEquals(authorService.update(author), authorDao.update(author));
    }

    @Test
    public void testAuthorServiceDelete() {
        int intStub = anyInt();

        authorService.delete(intStub);
        verify(authorDao).delete(intStub);

        Assert.assertEquals(authorService.delete(intStub), authorDao.delete(intStub));
    }
}
