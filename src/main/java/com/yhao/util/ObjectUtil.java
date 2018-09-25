package com.yhao.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象工具
 * @author long.hua
 * @version 1.0.0
 * @since 2016-02-24 23:01
 */
public class ObjectUtil {

    /**
     * 通过判空返回最子级对象的值，避免有一级为null报空指针异常。
     * @param fatherAndSonObjects 父子级对象，如获取p.p1.p11的值，则传入：p, p.p1, p.p1.p11
     * @param <T> 对象的类型
     * @return 最子级的值
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Object ...  fatherAndSonObjects){
        if(fatherAndSonObjects == null || fatherAndSonObjects.length == 0){
            return null;
        }

        for (Object t : fatherAndSonObjects) {
            if(t == null){
                return null;
            }
        }

        return (T) fatherAndSonObjects[fatherAndSonObjects.length-1];
    }

    public static String[] toArray(Enum[] enums){
        String[] values = new String[enums.length];

        for (int i = 0; i < enums.length; i++) {
            values[i] = enums[i].name();
        }

        return values;
    }

    public static void main(String[] args) {
        String s = get("1", 2, "3");
        System.out.println(s);
        Map map = new HashMap();
        System.out.println(map.get(null));
    }
}
