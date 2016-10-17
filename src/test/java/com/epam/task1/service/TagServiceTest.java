package com.epam.task1.service;

import com.epam.task1.dao.CrudDao;
import com.epam.task1.dao.impl.TagDaoImpl;
import com.epam.task1.model.Tag;
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
public class TagServiceTest {

    @InjectMocks
    TagService tagService;

    @Mock
    CrudDao tagDao;

    @Mock
    Tag tag;

    @Before
    public void setUpMocks() {
        BasicConfigurator.configure();

        when(tagService.create(tag)).thenReturn(true);
        when(tagService.read()).thenReturn(mock(List.class));
        when(tagService.create(tag)).thenReturn(true);
        when(tagService.delete(1)).thenReturn(true);

        when(tagDao.create(tag)).thenReturn(true);
        when(tagDao.read()).thenReturn(mock(List.class));
        when(tagDao.create(tag)).thenReturn(true);
        when(tagDao.delete(1)).thenReturn(true);
    }

    @Test
    public void testTagServiceCreate() {
        tagService.create(tag);
        verify(tagDao).create(tag);

        Assert.assertEquals(tagService.create(tag), tagDao.create(tag));
    }

    @Test
    public void testTagServiceRead() throws SQLException {
        tagService.read();
        verify(tagDao).read();

        Assert.assertEquals(tagService.read(), tagDao.read());
    }

    @Test
    public void testTagServiceUpdate() {
        tagService.update(tag);
        verify(tagDao).update(tag);

        Assert.assertEquals(tagService.update(tag), tagDao.update(tag));
    }

    @Test
    public void testTagServiceDelete() {
        int intStub = anyInt();

        tagService.delete(intStub);
        verify(tagDao).delete(intStub);

        Assert.assertEquals(tagService.delete(intStub), tagDao.delete(intStub));
    }
}
