package com.yhao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 
 * 
 * @author 刘斌华
 * @since 2016年05月10日
 *
 */
public final class Random {

	private static final Logger logger = LoggerFactory.getLogger(Random.class);

	private static java.util.Random random;
	private static final char[] randomArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	private static final int randomArrayLength = randomArray.length;

	static {
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException ex) {
			logger.error("Failed to initialize com.ykt.common.utils.Random.", ex);
		}
	}
	
	private static java.util.Random getRandom() {
		
		random.setSeed(System.currentTimeMillis());
		
		return random;
		
	}

	/**
	 * <h3>生成随机的字节数组</h3>
	 *
	 * <pre>
	 * MAX	127				MIN	-128
	 * </pre>
	 *
	 * @param length 数组的长度，大于0
	 * @return 字节数组
	 */
	public static byte[] bytes(int length) {
		
		if(length <= 0) {
			throw new IllegalArgumentException("The length should be greater than 0.");
		}

		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i ++) {
			bytes[i] = (byte) (getRandom().nextInt(256) - 128);
		}

		return bytes;

	}

	/**
	 * <h3>生成包含大小写字母的随机字符串</h3>
	 *
	 * @param length 生成的字符串的长度，大于0
	 * @return 包含大小写字母
	 */
	public static String string(int length) {

		if(length <= 0) {
			throw new IllegalArgumentException("The length should be greater than 0.");
		}

		StringBuilder result = new StringBuilder();
		for(int i = 0; i < length; i ++) {
			result.append(randomArray[Math.abs(getRandom().nextInt()) % (randomArrayLength - 10) + 10]);
		}

		return result.toString();

	}

	/**
	 * <h3>生成包含数字的随机字符串</h3>
	 *
	 * @param length 生成的字符串的长度，大于0
	 * @return 包含数字
	 */
	public static String numeric(int length) {

		if(length <= 0) {
			throw new IllegalArgumentException("The length should be greater than 0.");
		}

		StringBuilder result = new StringBuilder();
		for(int i = 0; i < length; i ++) {
			result.append(randomArray[Math.abs(getRandom().nextInt()) % 10]);
		}

		return result.toString();

	}

	/**
	 * <h3>生成包含数字、大小写字母的随机字符串</h3>
	 *
	 * @param length 生成的字符串的长度，大于0
	 * @return 包含数字、大小写字母
	 */
	public static String fixed(int length) {

		if(length <= 0) {
			throw new IllegalArgumentException("The length should be greater than 0.");
		}

		StringBuilder result = new StringBuilder();
		for(int i = 0; i < length; i ++) {
			result.append(randomArray[Math.abs(getRandom().nextInt()) % randomArrayLength]);
		}

		return result.toString();

	}

	/**
	 * <h3>生成随机的数字</h3>
	 *
	 * Returns a pseudorandom, uniformly distributed {@code int} value
	 * between 0 (inclusive) and the specified value (exclusive), drawn from
	 * this random number generator's sequence.  The general contract of
	 * {@code nextInt} is that one {@code int} value in the specified range
	 * is pseudorandomly generated and returned.  All {@code bound} possible
	 * {@code int} values are produced with (approximately) equal
	 * probability.  The method {@code nextInt(int bound)} is implemented by
	 * class {@code Random} as if by:
	 *  <pre> {@code
	 * public int nextInt(int bound) {
	 *   if (bound <= 0)
	 *     throw new IllegalArgumentException("bound must be positive");
	 *
	 *   if ((bound & -bound) == bound)  // i.e., bound is a power of 2
	 *     return (int)((bound * (long)next(31)) >> 31);
	 *
	 *   int bits, val;
	 *   do {
	 *       bits = next(31);
	 *       val = bits % bound;
	 *   } while (bits - val + (bound-1) < 0);
	 *   return val;
	 * }}</pre>
	 *
	 * <p>The hedge "approximately" is used in the foregoing description only
	 * because the next method is only approximately an unbiased source of
	 * independently chosen bits.  If it were a perfect source of randomly
	 * chosen bits, then the algorithm shown would choose {@code int}
	 * values from the stated range with perfect uniformity.
	 * <p>
	 * The algorithm is slightly tricky.  It rejects values that would result
	 * in an uneven distribution (due to the fact that 2^31 is not divisible
	 * by n). The probability of a value being rejected depends on n.  The
	 * worst case is n=2^30+1, for which the probability of a reject is 1/2,
	 * and the expected number of iterations before the loop terminates is 2.
	 * <p>
	 * The algorithm treats the case where n is a power of two specially: it
	 * returns the correct number of high-order bits from the underlying
	 * pseudo-random number generator.  In the absence of special treatment,
	 * the correct number of <i>low-order</i> bits would be returned.  Linear
	 * congruential pseudo-random number generators such as the one
	 * implemented by this class are known to have short periods in the
	 * sequence of values of their low-order bits.  Thus, this special case
	 * greatly increases the length of the sequence of values returned by
	 * successive calls to this method if n is a small power of two.
	 *
	 * @param bound the upper bound (exclusive).  Must be positive.
	 * @return the next pseudorandom, uniformly distributed {@code int}
	 *         value between zero (inclusive) and {@code bound} (exclusive)
	 *         from this random number generator's sequence
	 * @throws IllegalArgumentException if bound is not positive
	 * @see SecureRandom#nextInt(int)
	 */
	public static int integer(int bound) {
		
		return getRandom().nextInt(bound);
		
	}
	
	public static void main(String[] args) {

		for (int i = 0; i < 6888; i ++) {
			System.out.println(integer(6888));
		}
		
	}

}
