package com.tangyaya8.mmall.service.impl;

import com.tangyaya8.mmall.pojo.User;
import com.tangyaya8.mmall.repository.jpa.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IUserServiceImplTest {

    @Autowired
    private UserRepository repository;
    @Test
    public void login() {
        User user = repository.getUserByUserNameAndPassword("tangbaobao", "tangbaobao");
        System.out.println(user);
    }


}