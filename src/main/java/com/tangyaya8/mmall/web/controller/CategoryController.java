package com.tangyaya8.mmall.web.controller;

import com.tangyaya8.mmall.common.Resp;
import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.pojo.Category;
import com.tangyaya8.mmall.service.ICategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-05-10 16:57
 */
@RestController
@RequestMapping("/manage/category")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public Resp<List<Category>> getCategoryList() {
        List<Category> list = categoryService.getCategoryList();
        return Resp.success(list);
    }


    @PostMapping("/")
    public Resp<String> saveCategory(@Validated Category category) throws Exception {
        categoryService.saveCategory(category);
        return Resp.success();
    }

    @PutMapping("/{id}")
    public Resp<String> updateCategory(@PathVariable @Positive(message = "id 不正确") long id,
                                       @Validated Category category) throws Exception {
        categoryService.updateCategory(id, category);
        return Resp.success();
    }

    @GetMapping("/{id}")
    public Resp<Category> getCategoryById(@PathVariable @Positive(message = "id 不正确") long id) throws MallException {
        Category category = categoryService.getCategoryById(id);
        return Resp.success(category);
    }


    @GetMapping("parent/{parentId}")
    public Resp<List<Category>> getCategoryByParentId(@PathVariable @PositiveOrZero(message = "id 不正确") long parentId) throws MallException {
        List<Category> list = categoryService.getCategoryByParentId(parentId);
        return Resp.success(list);
    }

    //获取当前分类id及递归子节点categoryId
    @GetMapping("/recursive/{id}")
    public Resp<List<Long>> recursiveGetCategory(@PathVariable @Positive(message = "id 不正确") long id) throws MallException {
        List<Long> list = categoryService.recursiveGetCategory(id);
        return Resp.success(list);

    }

    @DeleteMapping("{id}")
    public Resp<String> deleteCategoryById(@PathVariable @Positive(message = "id 不正确") long id) throws Exception {
        categoryService.deleteCategoryById(id);
        return Resp.success();
    }
}
