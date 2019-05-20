package com.tangyaya8.mmall.service.impl;

import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.pojo.Category;
import com.tangyaya8.mmall.repository.jpa.CategoryRepository;
import com.tangyaya8.mmall.service.ICategoryService;
import com.tangyaya8.mmall.utils.CopyUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-05-10 17:04
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCategory(Category category) throws Exception {
        repository.save(category);
    }

    @Override
    public void updateCategory(long id, Category category) throws Exception {
        Category originCategory = repository.findById(id).orElseThrow(MallException::new);
        CopyUtils.copyBeanWithNoNull(originCategory, category);
        repository.save(category);
    }

    @Override
    public Category getCategoryById(long id) throws MallException {
        return repository.findById(id)
                .orElseThrow(() -> new MallException("品类不存在"));
    }

    @Override
    public void deleteCategoryById(long id) throws Exception {
        repository.deleteById(id);
    }

    @Override
    public List<Category> getCategoryList() {

        return repository.findAll();
    }

    @Override
    public List<Category> getCategoryByParentId(long parentId) {
        return repository.findAllByParentId(parentId);
    }


    @Override
    public List<Long> recursiveGetCategory(long id) throws MallException {
        List<Long> idList = new ArrayList<>();
        Category category = this.getCategoryById(id);
        this.recursiveCategory(category, idList);
        return idList;
    }


    /**
     * 递归获取品类及其子品类的内容
     *
     * @param category 种子品类
     * @param idList   用来保存id的集合
     */
    private void recursiveCategory(Category category, List<Long> idList) {
        if (category == null) {
            return;
        }
        List<Category> subCategory = repository.findAllByParentId(category.getId());
        idList.add(category.getId());
        subCategory.forEach(c -> recursiveCategory(c, idList));
    }


}
