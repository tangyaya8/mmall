package com.tangyaya8.mmall.service;

import com.github.pagehelper.PageInfo;
import com.tangyaya8.mmall.pojo.Order;
import com.tangyaya8.mmall.web.vo.OrderVO;

/**
 * @author tangxuejun
 * @version 2019-05-12 16:39
 */
public interface IOrderService {
    void saveOrder(Order order);

    PageInfo<OrderVO> getOrderList(int pageNum, int pageSize);
}
