package com.tangyaya8.mmall.repository.mybatis;

import com.tangyaya8.mmall.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> getProductList();

    List<Product> searchProduct(@Param("productId") long productId, @Param("productName")String productName);
}