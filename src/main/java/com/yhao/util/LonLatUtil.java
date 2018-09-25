package com.yhao.util;

/**
 * 经纬度计算工具
 * @author: long.hua
 * @date: 2016-02-22 17:12
 * @since 1.0
 */
public class LonLatUtil {


    public static double getDistance(double lat1, double lon1, double lat2, double lon2){
        double radLat1 = lat1 * Math.PI / 180;
        double radLat2 = lat2 * Math.PI / 180;
        double a = radLat1 - radLat2;
        double b = lon1 * Math.PI / 180 - lon2 * Math.PI / 180;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;

        return s;
    }

}
