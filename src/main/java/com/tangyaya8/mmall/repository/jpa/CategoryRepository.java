package com.tangyaya8.mmall.repository.jpa;

import com.tangyaya8.mmall.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-05-10 17:05
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByParentId(long parentId);
}
