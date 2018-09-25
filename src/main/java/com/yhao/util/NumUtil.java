package com.yhao.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author long.hua
 * @version 1.0.0
 * @since 2016-06-19 21:50
 */
public class NumUtil {


    /**
     * 格式化数字为两位小数结尾的数字
     */
    public static String format(double num) {
        try {
            DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
            df.applyPattern("0.00");
            return df.format(num);
        } catch (Exception e) {
            return String.valueOf(num);
        }
    }

    public static void main(String[] args) {
        System.out.println(format(10000));
    }

}
