package com.tangyaya8.mmall.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderItem {
    private long id;
    private long userId;
    private long orderNo;
    private long productId;
    private String productName;
    private String productImage;
    private double currentUnitPrice;
    private long quantity;
    private double totalPrice;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
