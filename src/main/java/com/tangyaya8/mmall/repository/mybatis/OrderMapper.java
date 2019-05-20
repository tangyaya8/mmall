package com.tangyaya8.mmall.repository.mybatis;

import com.tangyaya8.mmall.pojo.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getOrderList();
}