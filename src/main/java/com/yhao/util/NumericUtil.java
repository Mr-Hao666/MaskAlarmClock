package com.yhao.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 数字工具类
 *
 * @author 刘斌华
 * @since 2017年03月23日
 */
public final class NumericUtil {
	
	public static final BigDecimal DECIMAL_ZERO = new BigDecimal(0);
	public static final BigDecimal DECIMAL_ONE = new BigDecimal(1);
	public static final BigDecimal DECIMAL_HUNDRED = new BigDecimal(100);
	
	public static int forceInt(String numeric) {
		
		if (StringUtil.isBlank(numeric)) {
			return 0;
		}
		
		try {
			return Integer.parseInt(numeric);
		} catch (Throwable ignored) {}
		
		return 0;
		
	}
	
	public static long forceLong(String numeric) {
		
		if (StringUtil.isBlank(numeric)) {
			return 0L;
		}
		
		try {
			return Long.parseLong(numeric);
		} catch (Throwable ignored) {}
		
		return 0L;
		
	}
	
	public static double forceDouble(String numeric) {
		
		if (StringUtil.isBlank(numeric)) {
			return 0D;
		}
		
		try {
			return Double.parseDouble(numeric);
		} catch (Throwable ignored) {}
		
		return 0D;
		
	}
	
	public static BigDecimal forceDecimal(String numeric) {
		
		if (StringUtil.isBlank(numeric)) {
			return DECIMAL_ZERO;
		}
		
		try {
			return new BigDecimal(numeric);
		} catch (Throwable ignored) {}
		
		return DECIMAL_ZERO;
		
	}
	
	public static String toString(double numeric) {
		
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
		
		return format.format(numeric);
		
	}
	
	public static String toString(BigDecimal numeric) {
		
		return toString(numeric, 2);
		
	}
	
	public static String toString(BigDecimal numeric, int scale) {
		
		return numeric.setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString();
		
	}
	
}
