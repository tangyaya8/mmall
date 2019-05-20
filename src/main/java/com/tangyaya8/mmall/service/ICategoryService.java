package com.tangyaya8.mmall.service;

import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.pojo.Category;

import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-05-10 17:04
 */
public interface ICategoryService {
    List<Category> getCategoryList();

    void saveCategory(Category category) throws  Exception;

    void updateCategory(long id, Category category) throws MallException, Exception;

    Category getCategoryById(long id) throws MallException;

    void deleteCategoryById(long id) throws Exception;

    List<Long> recursiveGetCategory(long id) throws MallException;

    List<Category> getCategoryByParentId(long parentId);
}
