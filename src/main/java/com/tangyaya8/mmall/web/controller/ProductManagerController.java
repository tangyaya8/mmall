package com.tangyaya8.mmall.web.controller;

import com.github.pagehelper.PageInfo;
import com.tangyaya8.mmall.common.Resp;
import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.pojo.Product;
import com.tangyaya8.mmall.service.IProductService;
import com.tangyaya8.mmall.web.vo.ProductVO;
import org.springframework.web.bind.annotation.*;

/**
 * @author tangxuejun
 * @version 2019-05-13 10:06
 */
@RestController
@RequestMapping("/manager/product")
public class ProductManagerController {
    private final IProductService productService;

    public ProductManagerController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public Resp<String> saveProduct(Product product) throws Exception {
        productService.saveProduct(product);
        return Resp.success();
    }


    @PutMapping("/save/{id}")
    public Resp<String> updateProduct(Product product, @PathVariable long id) throws Exception {
        productService.updateProduct(product, id);
        return Resp.success();
    }

    @PutMapping("/status/{productId}")
    public Resp<String> updateProductStatus(@RequestParam int status,
                                            @PathVariable long productId) throws Exception {
        productService.updateProductStatus(productId, status);
        return Resp.success();
    }

    @GetMapping("/{id}")
    public Resp<ProductVO> getProductDetail(@PathVariable long id) throws MallException {
        ProductVO productVO = productService.getProductDetail(id);
        return Resp.success(productVO);
    }

    @GetMapping("/list/{pageNum}/{pageSize}")
    public void getProductList(@PathVariable int pageNum, @PathVariable int pageSize) {
        productService.getProductList(pageNum, pageSize);
    }

    @GetMapping("/search/")
    public Resp<PageInfo<ProductVO>> searchProduct(String productName, long productId, int pageNum, int pageSize) {
        PageInfo<ProductVO> pageInfo = productService.searchProduct(productName, productId, pageNum, pageSize);
        return Resp.success(pageInfo);
    }
}
