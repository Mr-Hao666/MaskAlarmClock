package com.yhao.util;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor;

public class AsyncThreadUtil {
    /**
     * 线程池，保持最小4个线程, 最大64个线程，30分钟若无调用，线程被回收,
     * 如果超过执行队列就拒绝并抛出异常RejectedExecutionException
     */
    private static final ExecutorService threadPool = new ThreadPoolExecutor(
            4, 64, 1800L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(20480),
            new ThreadPoolExecutor.AbortPolicy());


    public static void execute(Runnable cmd) throws Exception{
        threadPool.execute(cmd);
    }


    public static <V> Future<V> submit(Callable<V> cmd)  throws Exception{
        return threadPool.submit(cmd);
    }

}
