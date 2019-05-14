package com.hengmeng.hmkuaiyi.mvp.util;

import java.lang.reflect.ParameterizedType;

public class ReflectUtil {
    public static <T> T getT(Object o, int i) {
        try {
            ParameterizedType type = (ParameterizedType) o.getClass().getGenericSuperclass();
            if (type!=null){
                return ((Class<T>)type.getActualTypeArguments()[i]).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
