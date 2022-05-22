package com.atguigu.utils;

import com.atguigu.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    public static <T> T  copyParamToBean(Map value,T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成int数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
