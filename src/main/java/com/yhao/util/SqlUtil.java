package com.yhao.util;

import java.util.Collection;

/**
 * sql工具类
 *
 * @author long.hua
 * @version 1.0.0
 * @since 2016-05-24 23:55
 */
public class SqlUtil {


    public static <T> String placeholder(T[] list) {
        StringBuilder sb = new StringBuilder();
        String flag = ", ";
        for (int i = 0; i < list.length; i++) {
            sb.append("?").append(flag);
        }

        StringUtil.trimRight(sb, flag);

        return sb.toString();
    }

    public static <T> String placeholder(Collection<T> list) {
        return placeholder(list.toArray());
    }

}
