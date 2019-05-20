package com.tangyaya8.mmall.utils;

import com.google.common.collect.Lists;
import com.tangyaya8.mmall.pojo.User;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author tangxuejun
 * @version 2019-05-10 18:16
 */
public class CopyUtils {
    public static void copyBeanWithNoNull(Object source, Object target, String... ignore) throws Exception {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        List<String> ignoreCopyProperties = Lists.newArrayList();
        if (ignore != null) {
            ignoreCopyProperties.addAll(Arrays.asList(ignore));
        }
        Field[] fields = source.getClass().getDeclaredFields();
        for (int i = fields.length - 1; i >= 0; i--) {
            Field field = fields[i];
            //打开私有访问
            boolean canAccess = field.canAccess(source);
            if (!canAccess) {
                field.setAccessible(true);
            }
            Object fieldValue = field.get(source);
            if (fieldValue == null) {
                ignoreCopyProperties.add(field.getName());
            }
            field.setAccessible(canAccess);
        }
        BeanUtils.copyProperties(source, target, ignoreCopyProperties.toArray(new String[]{}));
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        User user1 = new User();
        user.setUserName("tangbaobao");
        user1.setUserName("tangdandan");
        user1.setPassword("meme");
        copyBeanWithNoNull(user,user1);
        System.out.println(user1);
    }
}
