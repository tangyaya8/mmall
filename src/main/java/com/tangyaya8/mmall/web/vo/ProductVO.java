package com.tangyaya8.mmall.web.vo;

import com.tangyaya8.mmall.pojo.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tangxuejun
 * @version 2019-05-13 11:17
 */
@Getter
@Setter
@ToString(callSuper = true)
 public class ProductVO extends Product {
    private String imageHost;
    private long parentCategoryId;
}
