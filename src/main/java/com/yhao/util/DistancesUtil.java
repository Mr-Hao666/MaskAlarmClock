package com.yhao.util;

/**
 * 计算两个经纬度之间的距离
 *
 * Created by 杨浩 on 2017/7/25.
 */
public class DistancesUtil {

    //private static double EARTH_RADIUS = 6378.137;
    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个经纬度之间的距离
     * @param lng1  一经度
     * @param lat1  一纬度
     * @param lng2  二经度
     * @param lat2  二纬度
     * @return 两位精度的距离（米）
     */
    public static double GetDistance(double lng1, double lat1, double lng2, double lat2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }



    public static void main(String[] args) {
        long before = System.currentTimeMillis();
        System.out.println(before);
        System.out.println(DistancesUtil.GetDistance(113.9642241,22.54524,113.964334,22.54423));
        long now = System.currentTimeMillis();
        System.out.println(now-before);
    }
}
