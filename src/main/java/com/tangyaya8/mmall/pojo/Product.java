package com.tangyaya8.mmall.pojo;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mmall_product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long categoryId;
    private String productName;
    private String subTitle;
    private String mainImage;
    private String subImages;
    private String detail;
    private BigDecimal price;
    private Long stock;
    private Long status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Product( Long id,Long categoryId, String productName, String subTitle, String mainImage, String subImages, String detail, BigDecimal price, Long stock, Long status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.categoryId = categoryId;
        this.productName = productName;
        this.subTitle = subTitle;
        this.mainImage = mainImage;
        this.subImages = subImages;
        this.detail = detail;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
