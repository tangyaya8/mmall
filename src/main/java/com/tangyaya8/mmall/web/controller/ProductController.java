package com.tangyaya8.mmall.web.controller;

import com.tangyaya8.mmall.common.Resp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-05-13 09:18
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @PostMapping("/list")
    public Resp<List> saveOrUpdate() {
        return Resp.success();
    }
}
