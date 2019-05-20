package com.tangyaya8.mmall.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Cart {
    private long id;
    private long userId;
    private long productId;
    private long quantity;
    private long checked;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
