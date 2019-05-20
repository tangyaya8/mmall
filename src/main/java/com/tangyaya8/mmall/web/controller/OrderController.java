package com.tangyaya8.mmall.web.controller;

import com.github.pagehelper.PageInfo;
import com.tangyaya8.mmall.common.Resp;
import com.tangyaya8.mmall.pojo.Order;
import com.tangyaya8.mmall.service.IOrderService;
import com.tangyaya8.mmall.web.vo.OrderVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

/**
 * @author tangxuejun
 * @version 2019-05-12 16:23
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public Resp<String> saveOrder(@Validated Order order) {
        orderService.saveOrder(order);
        return Resp.success();
    }

    @GetMapping("/list/{pageSize}/{pageNum}")
    public Resp<PageInfo<OrderVO>> getOrderList(@PathVariable int pageSize,
                                                @PathVariable int pageNum) {
        PageInfo<OrderVO> orderList = orderService.getOrderList(pageSize, pageNum);
        return Resp.success(orderList);
    }

    @GetMapping("/{id}")
    public Resp<Order> getOrderById(@PathVariable @Positive(message = "订单号不正确") long id) {

        return Resp.success();
    }

    @PutMapping("cancel/{id}")
    public Resp<String> cancelOrder(@PathVariable @Positive(message = "订单号不正确") long id) {
        return Resp.success();
    }


}
