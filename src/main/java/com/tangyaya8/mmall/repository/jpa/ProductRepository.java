package com.tangyaya8.mmall.repository.jpa;

import com.tangyaya8.mmall.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tangxuejun
 * @version 2019-05-13 10:50
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
