package com.tangyaya8.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangyaya8.mmall.pojo.Order;
import com.tangyaya8.mmall.repository.mybatis.OrderMapper;
import com.tangyaya8.mmall.service.IOrderService;
import com.tangyaya8.mmall.web.vo.OrderVO;
import org.springframework.stereotype.Service;

/**
 * @author tangxuejun

 * @version 2019-05-12 16:40
 */
@Service
public class OrderServiceImpl implements IOrderService {
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    @Override
    public void saveOrder(Order order) {

    }

    public PageInfo<OrderVO> getOrderList(int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(orderMapper::getOrderList);
    }
}
