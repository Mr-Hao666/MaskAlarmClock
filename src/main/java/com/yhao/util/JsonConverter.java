package com.yhao.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * Json解析以格式化
 *
 * @author long.hua
 * @version 1.0.0
 * @since 2015-12-27 22:54
 */
public class JsonConverter {


    public static String format(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T parse(String str, Class<T> clazz) {
        return JSON.parseObject(str, clazz);
    }

    public static <T> List<T> parseArray(String str, Class<T> clazz) {
        return JSON.parseArray(str, clazz);
    }

    public static <T> T parse(String str, TypeReference<T> type) {
        return JSON.parseObject(str, type);
    }

    public static String pretty(String jsonStr) {
        int level = 0;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == sb.charAt(sb.length() - 1)) {
                sb.append(getLevel(level));
            }
            switch (c) {
                case '{':
                case '[':
                    sb.append(c + "\n");
                    level++;
                    break;
                case ',':
                    if(isRealComma(jsonStr, i)){
                        sb.append(c + "\n");
                    }
                    break;
                case '}':
                case ']':
                    sb.append("\n");
                    level--;
                    sb.append(getLevel(level));
                    sb.append(c);
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }

        return sb.toString();
    }

    private static String getLevel(int level) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    // 判断标准的json格式，属性名必须用双引号或单引号括住
    private static boolean isRealComma(String jsonStr, int commaIndex) {
        // 引号使用情况：{"": "", "": ""} 逗号前后必须是双引号 或 单引号，且未转义；

        int step = 1;
        char c = '\0';

        // 合法逗号与 “引号” 或 “{” 或 “[” 之间只能是空格或制表符
        while (commaIndex + step < jsonStr.length()) {
            c = jsonStr.charAt(commaIndex + step);
            if(c == ' ' || c == '\t'){
                step++;
            } else {
                break;
            }
        }

        // 判断逗号后面是否有一个非转义单双引号 或 { 或 [
        return (c == '"' || c == '{' || c == '[');
    }

    public static void main(String[] args) {
        String s = JsonConverter.pretty("{\"message\":\"操作成功\",\"page\":null,\"data\":{\"summary\":\"<div style=\\\"margin:0px 0px 10px;padding:0px 10px 10px;color:#666666;font-family:Arial, Verdana, 宋体;background-color:#FFFFFF;\\\">\\r\\n\\t<ul>\\r\\n\\t\\t<li>\\r\\n\\t\\t\\t商品名称：努比亚小牛5 Z11mini\\r\\n\\t\\t<\\/li>\\r\\n\\t\\t<li>\\r\\n\\t\\t\\t商品编号：2882978\\r\\n\\t\\t<\\/li>\\r\\n\\t\\t<li>\\r\\n\\t\\t\\t商品毛重：460.00g\\r\\n\\t\\t<\\/li>\\r\\n\\t<\\/ul>\\r\\n<\\/div>\\r\\n<div style=\\\"margin:10px 0px;padding:0px;background:#F7F7F7;color:#666666;font-family:Arial, Verdana, 宋体;\\\">\\r\\n\\t<div style=\\\"margin:0px;padding:0px;background-color:#FFFFFF;\\\">\\r\\n\\t\\t<div style=\\\"margin:0px;padding:0px;\\\">\\r\\n\\t\\t\\t<div style=\\\"margin:0px;padding:0px;\\\">\\r\\n\\t\\t\\t\\t<table width=\\\"750\\\" border=\\\"0\\\" align=\\\"center\\\" cellpadding=\\\"0\\\" cellspacing=\\\"0\\\" style=\\\"font-family:微软雅黑, Arial;\\\">\\r\\n\\t\\t\\t\\t\\t<tbody>\\r\\n\\t\\t\\t\\t\\t\\t<tr>\\r\\n\\t\\t\\t\\t\\t\\t\\t<td>\\r\\n\\t\\t\\t\\t\\t\\t\\t\\t<img width=\\\"750\\\" alt=\\\"\\\" src=\\\"http:\\/\\/img12.360buyimg.com\\/cms\\/jfs\\/t1861\\/66\\/2736430104\\/286301\\/4154574b\\/5715e89bN75ea0fc8.jpg\\\" \\/> \\r\\n\\t\\t\\t\\t\\t\\t\\t<\\/td>\\r\\n\\t\\t\\t\\t\\t\\t<\\/tr>\\r\\n\\t\\t\\t\\t\\t<\\/tbody>\\r\\n\\t\\t\\t\\t<\\/table>\\r\\n\\t\\t\\t<\\/div>\\r\\n\\t\\t<\\/div>\\r\\n\\t<\\/div>\\r\\n<\\/div>\",\"goodsId\":579,\"showInitPrice\":0,\"shopSummary\":\"跨境鲜果农批预订选购平台\",\"unitSummary\":\"6斤\",\"id\":579,\"initPrice\":60.0,\"stock\":9999,\"shopId\":22,\"name\":\"鸡心黄皮\",\"goodsPrice\":60.0,\"goodsUnit\":\"份\",\"updatedTime\":\"18:04\",\"marketId\":1,\"icon\":\"http:\\/\\/125.94.215.62:8080\\/download\\/goods\\/image\\/2016-07-07\\/13b8f474-edd3-49c1-9689-0e4ce145e80b.jpg\",\"createdTime\":\"2016-07-07 11:55:04\",\"status\":\"IN\",\"unit\":\"份\",\"mgCatId\":10,\"shopName\":\"小果鲜\",\"original\":\"国产\",\"salePrice\":60.0,\"mediaSources\":[{\"id\":592,\"content\":\"http:\\/\\/125.94.215.62:8080\\/download\\/goods\\/image\\/2016-07-07\\/13b8f474-edd3-49c1-9689-0e4ce145e80b.jpg\",\"cover\":\"http:\\/\\/125.94.215.62:8080\\/download\\/goods\\/image\\/2016-07-07\\/13b8f474-edd3-49c1-9689-0e4ce145e80b.jpg\",\"sort\":0,\"createdTime\":\"2016-10-04 18:04:40\",\"name\":null,\"bizType\":\"GOODS\",\"bizSubType\":\"GOODS_HEAD\",\"bizId\":579,\"updatedTime\":\"2016-10-04 18:04:40\",\"mediaType\":\"IMAGE\"}],\"goodsName\":\"鸡心黄皮\",\"sgCatId\":49,\"unitId\":273,\"shopLogo\":\"http:\\/\\/125.94.215.62:8080\\/download\\/shop\\/image\\/2016-07-07\\/36376dba-77da-4908-adaf-b15bdf276867.jpg\"},\"code\":0}");
        System.out.println(s);
    }
}
