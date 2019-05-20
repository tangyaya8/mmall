package com.tangyaya8.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.pojo.Category;
import com.tangyaya8.mmall.pojo.Product;
import com.tangyaya8.mmall.repository.jpa.CategoryRepository;
import com.tangyaya8.mmall.repository.jpa.ProductRepository;
import com.tangyaya8.mmall.repository.mybatis.ProductMapper;
import com.tangyaya8.mmall.service.IProductService;
import com.tangyaya8.mmall.web.vo.ProductVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.tangyaya8.mmall.common.ErrCode.ILLEGAL_PARAM;

/**
 * @author tangxuejun
 * @version 2019-05-13 10:13
 */
@Service
public class ProductServiceImpl implements IProductService {

    private final ProductMapper productDao;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productDao = productMapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveProduct(Product product) throws Exception {
        if (product == null) {
            throw new MallException(ILLEGAL_PARAM);
        }
        productDao.insertSelective(product);
    }

    @Override
    public void updateProduct(Product product, long productId) throws Exception {
        if (product == null) {
            throw new MallException(ILLEGAL_PARAM);
        }
        //将子图设置为主图
        if (StringUtils.isNotBlank(product.getSubImages())) {
            product.setMainImage(product.getSubImages());
        }
        productRepository.findById(productId).orElseThrow(() -> new MallException("商品不存在"));
        product.setId(productId);
        productDao.updateByPrimaryKey(product);
    }

    @Override
    public void updateProductStatus(long productId, long status) throws Exception {
        productRepository.findById(productId).orElseThrow(() -> new MallException("商品不存在"));
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        productDao.updateByPrimaryKeySelective(product);
    }

    @Override
    public ProductVO getProductDetail(long id) throws MallException {
        Product product = productRepository.findById(id).orElseThrow(() -> new MallException("商品不存在"));
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);
        productVO.setImageHost("");
        Category category = categoryRepository.getOne(product.getCategoryId());
        productVO.setParentCategoryId(category.getParentId());
        return productVO;
    }

    @Override
    public PageInfo<ProductVO> getProductList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productDao.getProductList();
        return assemble(productList);
    }

    @Override
    public PageInfo<ProductVO> searchProduct(String productName, long productId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productDao.searchProduct(productId, productName);
        return assemble(productList);
    }


    private PageInfo<ProductVO> assemble(List<Product> productList) {
        List<ProductVO> productVOList = productList.stream().map(this::trans).collect(Collectors.toList());
        PageInfo productPageInfo = new PageInfo<>(productList);
        productPageInfo.setList(productVOList);
        return productPageInfo;
    }

    private ProductVO trans(Product product) {
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);
        return productVO;
    }

}
