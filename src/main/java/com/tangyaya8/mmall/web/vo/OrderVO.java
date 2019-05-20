package com.tangyaya8.mmall.web.vo;

import com.tangyaya8.mmall.pojo.Order;
import com.tangyaya8.mmall.pojo.OrderItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-05-12 16:52
 */
@Getter
@Setter
@ToString(callSuper = true)
public class OrderVO extends Order {
    private long orderNo;
    private List<OrderItem> orderItemList;
}
