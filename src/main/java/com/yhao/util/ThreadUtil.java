package com.yhao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author long.hua
 * @since 1.0.0
 * Created on 2016-09-22 16:16
 */
public class ThreadUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    private static final int MAX_RETRY =50;


    public static long getId(){
        return Thread.currentThread().getId();
    }


    public static String getName(){
        return Thread.currentThread().getName();
    }


    /**
     * 休息指定毫秒数
     */
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            LOGGER.error("方法 sleep 被打断...", e);
        }
    }

    /**
     * 最多休息指定毫秒数
     */
    public static void sleepMaxMilliseconds(int maxMilliseconds) {
        try {
            int seconds = (int) (Math.random() * (maxMilliseconds + 1));

            Thread.sleep(seconds);

        } catch (InterruptedException e) {
            LOGGER.error("方法 sleepMaxCountSeconds 被打断...", e);
        }
    }

    /**
     * 最多休息指定几毫秒到几毫秒
     */
    public static void sleepRangeMilliseconds(int minMilliseconds, int maxMilliseconds) {
        try {

            int milliseconds = (int) (Math.random() * (maxMilliseconds + 1));
            for (int i = 0; milliseconds < minMilliseconds && i < MAX_RETRY; i++) {
                milliseconds = (int) (Math.random() * (maxMilliseconds + 1));
            }

            if (milliseconds < minMilliseconds) {
                milliseconds = minMilliseconds;
            }

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {
            LOGGER.error("方法 sleepRangeSeconds 被打断...", e);
        }
    }

}
