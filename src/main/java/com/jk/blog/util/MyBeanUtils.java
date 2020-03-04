package com.jk.blog.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-15 12:53
 **/
public class MyBeanUtils {

    /**
    * @Description: getNullPropertyNames 获取所有属性为空的属性名数组
    * @Param: [source]
    * @return: java.lang.String[]
    * @Author: Jiakun
    * @time: 2020/2/15 13:00
    */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }

}