package com.tangyaya8.mmall.service;

import com.github.pagehelper.PageInfo;
import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.pojo.Product;
import com.tangyaya8.mmall.web.vo.ProductVO;

/**
 * @author tangxuejun
 * @version 2019-05-13 10:11
 */
public interface IProductService {
    void saveProduct(Product product) throws Exception;

    void updateProduct(Product product, long productId) throws  Exception;

    void updateProductStatus(long productId, long status) throws Exception;

    ProductVO getProductDetail(long id) throws MallException;

    PageInfo<ProductVO> getProductList(int pageNum, int pageSize);

    PageInfo<ProductVO> searchProduct(String productName, long productId, int pageNum, int pageSize);
}
