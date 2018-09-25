package com.yhao.util;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 杨浩
 * @create 2017-11-27 15:06
 **/
public class FontUtil {

    private static Font definedFont = null;
    private static Long flag = 0L;

    public static Font loadCustomFont(String name, int style, int fs) {
        if (flag == 0L) {
            synchronized (flag) {
                if (flag == 0L) {
                    InputStream is = null;
                    BufferedInputStream bis = null;
                    try {
                        switch (name) {
                            case "simsun":
                                name = "simsun";
                                break;
                            default:
                                name = "simsun";
                                break;
                        }
                        definedFont = Font.createFont(Font.TRUETYPE_FONT, FontUtil.class.getResourceAsStream("/font/" + name + ".ttc"));
                        //设置字体大小，float型
                        definedFont = definedFont.deriveFont(style, fs);
                    } catch (FontFormatException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (null != bis) {
                                bis.close();
                            }
                            if (null != is) {
                                is.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return definedFont;
    }


    public static void main(String[] args) {
        FontUtil.loadCustomFont("", Font.PLAIN, 14);


    }


}
