package com.epam.task1.service;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.impl.AuthorDaoImpl;
import com.epam.task1.model.Author;
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

public class AuthorServiceTest {

    @Autowired
    @InjectMocks
    AuthorService authorService;
    CrudDao authorDao;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();

        authorDao = mock(AuthorDaoImpl.class);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthorServiceCreate() {
        Author author = mock(Author.class);

        authorService.create(author);
        verify(authorDao).create(author);
    }

    @Test
    public void testAuthorServiceRead() throws SQLException {
        authorService.read();
        verify(authorDao).read();
    }

    @Test
    public void testAuthorServiceReadById() throws SQLException {
        int intStub = anyInt();

        authorService.read(intStub);
        verify(authorDao).read(intStub);
    }

    @Test
    public void testAuthorServiceUpdate() {
        Author author = any(Author.class);

        authorService.update(author);
        verify(authorDao).update(author);
    }

    @Test
    public void testAuthorServiceDelete() {
        int intStub = anyInt();

        authorService.delete(intStub);
        verify(authorDao).delete(intStub);
    }
}
