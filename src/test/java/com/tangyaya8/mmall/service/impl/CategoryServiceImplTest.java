package com.tangyaya8.mmall.service.impl;

import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.service.ICategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private ICategoryService service;

    @Test
    public void recursiveGetCategory() throws MallException {
        List<Long> longs = service.recursiveGetCategory(100004);
        longs.forEach(System.out::println);
    }

}