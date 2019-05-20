package com.tangyaya8.mmall.service.impl;

import com.github.pagehelper.PageInfo;
import com.tangyaya8.mmall.service.IProductService;
import com.tangyaya8.mmall.web.vo.ProductVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceImplTest {
    @Autowired
    private IProductService service;
    @Test
    public void getProductList() {
        PageInfo<ProductVO> productList = service.getProductList(1, 10);
        System.out.println(productList);
    }

    @Test
    public void searchProduct() {
        PageInfo<ProductVO> pageInfo = service.searchProduct("Apple", -1, 1, 10);
        System.out.println(pageInfo);
    }
}