package com.yhao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程执行工具
 *
 * @author 刘斌华
 * @since 2016年08月06日
 */
public final class ThreadPoolExecutor {

	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolExecutor.class);

	private static ExecutorService cachedExecutor = new java.util.concurrent.ThreadPoolExecutor(128, Integer.MAX_VALUE,
			10L, TimeUnit.MINUTES, new SynchronousQueue<Runnable>());
	private static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(256);

	static {
		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				cachedExecutor.shutdown();
			}
		}));
		runtime.addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				scheduledExecutor.shutdown();
			}
		}));
	}

	/**
	 * <h3>立即执行</h3>
	 *
	 * @param runnable 要执行的线程
	 */
	public static void execute(Runnable runnable) {

		if (runnable != null) {
			try {
				cachedExecutor.execute(runnable);
			} catch (Throwable ex) {
				if (!(ex instanceof RejectedExecutionException)) {
					logger.error("Exception occurred while executing the command.", ex);
				}
			}
		}

	}

	/**
	 * <h3>延迟指定时间后后执行</h3>
	 *
	 * @param runnable 要执行的线程
	 * @param seconds 延迟的秒数
	 */
	public static void delay(Runnable runnable, int seconds) {

		if (seconds <= 0L) {
			throw new IllegalArgumentException("If you want to execute immediately, please use execute(Runnable).");
		}

		if (runnable != null) {
			try {
				scheduledExecutor.schedule(runnable, seconds, TimeUnit.SECONDS);
			} catch (Throwable ex) {
				if (!(ex instanceof RejectedExecutionException)) {
					logger.error("Exception occurred while executing the command.", ex);
				}
			}
		}

	}
	
}
