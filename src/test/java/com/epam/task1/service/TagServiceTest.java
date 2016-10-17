package com.epam.task1.service;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.impl.TagDaoImpl;
import com.epam.task1.model.Tag;
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

public class TagServiceTest {

    @Autowired
    @InjectMocks
    TagService tagService;
    CrudDao tagDao;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();

        tagDao = mock(TagDaoImpl.class);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTagServiceCreate() {
        Tag tag = mock(Tag.class);

        tagService.create(tag);
        verify(tagDao).create(tag);
    }

    @Test
    public void testTagServiceRead() throws SQLException {
        tagService.read();
        verify(tagDao).read();
    }

    @Test
    public void testTagServiceUpdate() {
        Tag tag = any(Tag.class);

        tagService.update(tag);
        verify(tagDao).update(tag);
    }

    @Test
    public void testTagServiceDelete() {
        int intStub = anyInt();

        tagService.delete(intStub);
        verify(tagDao).delete(intStub);
    }
}
